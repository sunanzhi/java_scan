package com.eolink.support;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.DataKey;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


public class JavaParserTest {
    CompilationUnit cu;
    ParseResult<CompilationUnit> result;
    @BeforeEach
    public void runBeforeTestMethod() throws Exception{
//        File javaFile = new File("U:\\data\\web\\ams_independent\\export\\api_doc\\322f62469c5e3c7dc3e58f5a4d1ea399\\code\\src\\main\\java\\com\\eolink\\common\\controller\\ExampleController.java");
        File javaFile = new File("D:\\projects\\java_projects\\support\\src\\main\\java\\com\\eolink\\common\\controller\\ExampleController.java");
        var javaParser = new JavaParser();
        result = javaParser.parse(javaFile);

        result.ifSuccessful(compilationUnit -> {
            cu = compilationUnit;
        });
        System.out.println("------runBeforeTestMethod------");
    }

    @Test
    void t1() {
        // 获取所有注释信息
        List<Comment> comments = cu.getAllComments();
        System.out.println(comments);
    }

    @Test
    void t2() {
        for (Comment comment : cu.getAllContainedComments()) {
            // 获取注释内容
            String content = comment.getContent();
            System.out.println(content);
        }
    }

    @Test
    void t3() {
        for (Comment comment : cu.getAllContainedComments()) {
            // 解析注释
            Javadoc parse = comment.asJavadocComment().parse();
            String methodDesc = parse.getDescription().toText();
            System.out.println("方法注释:" + methodDesc);
            List<JavadocBlockTag> blockTags = parse.getBlockTags();
            for (JavadocBlockTag javadocBlockTag: blockTags) {
                Optional<String> name = javadocBlockTag.getName();
                name.ifPresent(k -> {
                    String text = javadocBlockTag.getContent().getElements().get(0).toText();
                    System.out.println("参数注释: " + k + ":" + text);
                });
            }
        }
    }

    @Test
    void t4() {
        Optional<ClassOrInterfaceDeclaration> exampleController = cu.getClassByName("ExampleController");
        exampleController.flatMap(NodeWithJavadoc::getJavadoc).ifPresent(javadoc -> {
            javadoc.addBlockTag("RequestMapping");
            // 解析注释
            String methodDesc = javadoc.getDescription().toText();
            System.out.println("方法注释:" + methodDesc);
            List<JavadocBlockTag> blockTags = javadoc.getBlockTags();
            for (JavadocBlockTag javadocBlockTag : blockTags) {
                String name = javadocBlockTag.getTagName();
                String content = javadocBlockTag.getContent().toText();
                System.out.println("参数注释: " + name + ":" + content);
            }
        });
    }

    @Test
    void t5() {
        result.getResult().ifPresent(unit -> {
            unit.getTypes().forEach(typeDeclaration -> {
                typeDeclaration.getAnnotations().forEach(annotation -> {
                    System.out.println("------typeDeclaration------");
                    System.out.println("annotation.getName():" + annotation.getName() + "");
                    if (annotation.getName().asString().equals("RequestMapping")) {
                        annotation.getChildNodes().forEach(node -> {
                            System.out.println(node.toString());
                        });
                    }
                });
                System.out.println(typeDeclaration.getName());
                //获取方法
                typeDeclaration.getMethods().forEach(method -> {
                    System.out.println("methodName:"+method.getName());
                    method.getAnnotations().forEach(annotation -> {
                        if (annotation.getName().asString().equals("RequestMapping")) {
                            annotation.getChildNodes().forEach(node -> {
                                System.out.println(node.toString());
                            });
                        }
                    });
                });
            });
        });
    }
}
