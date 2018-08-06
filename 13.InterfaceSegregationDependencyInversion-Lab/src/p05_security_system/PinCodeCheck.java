package p05_security_system;

public class PinCodeCheck extends SecurityCheck {

    private PinCodeRequest securityUI;

    public PinCodeCheck(PinCodeRequest securityUI) {
        this.securityUI = securityUI;
    }

    @Override
    public boolean validateUser() {
        int pin = securityUI.requestPinCode();
        if (isValid(pin)) {
            return true;
        }

        return false;
    }

    private boolean isValid(int pin) {
        return true;
    }
}
