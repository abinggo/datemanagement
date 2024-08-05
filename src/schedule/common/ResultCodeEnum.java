package schedule.common;

public enum ResultCodeEnum {

    SUCCESS(200,"success"),
    USERNAME_ERROR(501,"usernameError"),
    PASSWORD_ERROR(503,"passwordError"),
    NOTLOFIN(504,"nologin"),
    ADDERROR(506,"adderror"),
    SAVEERROR(507,"saveerror"),
    DELETEERROR(508,"deleteerror"),
    USERNAME_EXIST(505,"usernameExist");


    private Integer code;
    private String message;
    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
