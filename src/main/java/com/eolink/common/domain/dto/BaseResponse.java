package com.eolink.common.domain.dto;

import com.eolink.common.enumeration.ErrorCodeEnums;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author sunanzhi work
 */
@Data
@Builder
public class BaseResponse<T> implements Serializable {
	public static final String STR_SUCCESS = "SUCCESS";
	public static final String STR_FAIL = "FAIL";

	@Serial
	private static final long serialVersionUID = 5680880623911175914L;

	/**
	 * 通用返回格式的数据字段
	 */

	private T data;
	/**
	 * 通用返回格式的信息部分
	 */
	
	private String message;
	/**
	 * 实体类属性使用包装类型
	 * 通用返回格式的代码
	 */

	private Long code;
	/**
	 * 错误异常名称
	 */

	private String exception;
	/**
	 * 系统时间
	 */

	private LocalDateTime time;

	/**
	 * 成功时返回的JSON数据
	 *
	 * @return {@code BaseResponse<String>}
	 */
	public static BaseResponse<String> success() {
		return BaseResponse.<String>builder()
				.code(ErrorCodeEnums.SUCCESS.getErrorCode())
				.message(STR_SUCCESS)
				.time(LocalDateTime.now())
				.build();
	}

	/**
	 * 成功时返回的JSON数据
	 *
	 * @param data
	 *         数据
	 * @param <T>
	 *         类型
	 *
	 * @return {@code BaseResponse<?>}
	 */
	public static <T> BaseResponse<T> success(T data) {
		return BaseResponse.<T>builder()
				.code(ErrorCodeEnums.SUCCESS.getErrorCode())
				.message(STR_SUCCESS)
				.data(data)
				.time(LocalDateTime.now())
				.build();
	}

	/**
	 * 失败时返回的JSON数据
	 *
	 * @param data
	 *         数据
	 * @param <T>
	 *         类型
	 *
	 * @return {@code BaseResponse<?>}
	 */
	public static <T> BaseResponse<T> fail(T data) {
		return BaseResponse.<T>builder()
				.code(ErrorCodeEnums.FAIL_REQ.getErrorCode())
				.message(STR_FAIL)
				.data(data)
				.time(LocalDateTime.now())
				.build();
	}

	/**
	 * 失败时返回的JSON数据，携带业务错误码、错误信息 message，不带数据
	 *
	 * @param code
	 *         错误返回码
	 * @param message
	 *         错误异常信息 message
	 * @param exception
	 *         错误异常名称
	 *
	 * @return {@code BaseResponse<String>}
	 */
	public static BaseResponse<String> fail(Long code, String message, String exception) {
		return BaseResponse.<String>builder()
				.code(code)
				.message(message)
				.exception(exception)
				.time(LocalDateTime.now())
				.build();
	}

	/**
	 * 失败时返回的JSON数据，携带其他通用错误码、错误信息 message，不带数据
	 *
	 * @param message
	 *         错误信息message
	 * @param exception
	 *         错误异常名称
	 *
	 * @return {@code BaseResponse<String>}
	 */
	public static BaseResponse<String> fail(String message, String exception) {
		return BaseResponse.<String>builder()
				.code(ErrorCodeEnums.FAIL_REQ.getErrorCode())
				.message(message)
				.exception(exception)
				.time(LocalDateTime.now())
				.build();
	}
}
