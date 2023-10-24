package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ErrorDTO {

    int status;
    String error;
    String message;
//    String path;



}
