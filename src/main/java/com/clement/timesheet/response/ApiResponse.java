package com.clement.timesheet.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean isSuccess;
    private Error[] errors;
    private T data;
    private long timestamp;

    public static <T> ApiResponseBuilder<T> builder() {
        return new ApiResponseBuilder();
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public Error[] getErrors() {
        return this.errors;
    }

    public T getData() {
        return this.data;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setSuccess(final boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public void setErrors(final Error[] errors) {
        this.errors = errors;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ApiResponse)) {
            return false;
        } else {
            ApiResponse<?> other = (ApiResponse)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isSuccess() != other.isSuccess()) {
                return false;
            } else if (this.getTimestamp() != other.getTimestamp()) {
                return false;
            } else if (!Arrays.deepEquals(this.getErrors(), other.getErrors())) {
                return false;
            } else {
                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ApiResponse;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + (this.isSuccess() ? 79 : 97);
        long $timestamp = this.getTimestamp();
        result = result * 59 + (int)($timestamp >>> 32 ^ $timestamp);
        result = result * 59 + Arrays.deepHashCode(this.getErrors());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        boolean var10000 = this.isSuccess();
        return "ApiResponse(isSuccess=" + var10000 + ", errors=" + Arrays.deepToString(this.getErrors()) + ", data=" + this.getData() + ", timestamp=" + this.getTimestamp() + ")";
    }

    public ApiResponse() {
    }

    public ApiResponse(final boolean isSuccess, final Error[] errors, final T data, final long timestamp) {
        this.isSuccess = isSuccess;
        this.errors = errors;
        this.data = data;
        this.timestamp = timestamp;
    }

    public static class ApiResponseBuilder<T> {
        private boolean isSuccess;
        private Error[] errors;
        private T data;
        private long timestamp;

        ApiResponseBuilder() {
        }

        public ApiResponseBuilder<T> isSuccess(final boolean isSuccess) {
            this.isSuccess = isSuccess;
            return this;
        }

        public ApiResponseBuilder<T> errors(final Error[] errors) {
            this.errors = errors;
            return this;
        }

        public ApiResponseBuilder<T> data(final T data) {
            this.data = data;
            return this;
        }

        public ApiResponseBuilder<T> timestamp(final long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ApiResponse<T> build() {
            return new ApiResponse(this.isSuccess, this.errors, this.data, this.timestamp);
        }

        public String toString() {
            boolean var10000 = this.isSuccess;
            return "ApiResponse.ApiResponseBuilder(isSuccess=" + var10000 + ", errors=" + Arrays.deepToString(this.errors) + ", data=" + this.data + ", timestamp=" + this.timestamp + ")";
        }
    }
}
