package schule.bbs2.j2023.efi3b.computerroomreservation.controller.params;

public interface Validatable {

    /**
     * Throw Runtime Exception if validation failed
     */
    public void validate();
}
