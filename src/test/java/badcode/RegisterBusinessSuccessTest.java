package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterBusinessSuccessTest {

    @Test
    @DisplayName("exp ของ speaker มีค่าและ return ออกมา")
    public void case06() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("chai");
        speaker.setLastName("Foo");
        speaker.setEmail("chai.Foo@gmail.com");
        speaker.setExp(1);
        assertEquals(1, registerBusiness.register(new SpeakerRepository() {
            @Override
            public Integer saveSpeaker(Speaker speaker) {
                return speaker.getExp();
            }
        }, speaker));
    }

}