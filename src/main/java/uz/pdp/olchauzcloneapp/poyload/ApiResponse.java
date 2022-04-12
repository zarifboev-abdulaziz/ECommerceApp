package uz.pdp.olchauzcloneapp.poyload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {

    private String message;
    private boolean status;
    private Object object;

    public ApiResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }
}
