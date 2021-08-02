package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterBusinessTest {

    @Test
    @DisplayName("Firstname ของ speaker มีค่าเป็น null จึงเกิด exception ขึ้นมา")
    public void case01() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Exception exception = assertThrows(ArgumentNullException.class, () -> registerBusiness.register(null, new Speaker()));
        assertEquals("First name is required.", exception.getMessage());
    }

    @Test
    @DisplayName("Lastname ของ speaker มีค่าเป็น null จึงเกิด exception ขึ้นมา")
    public void case02() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("chai");
        Exception exception = assertThrows(ArgumentNullException.class, () -> registerBusiness.register(null, speaker));
        assertEquals("Last name is required.", exception.getMessage());
    }

    @Test
    @DisplayName("Email ของ speaker มีค่าเป็น null จึงเกิด exception ขึ้นมา")
    public void case03() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("chai");
        speaker.setLastName("Foo");
        Exception exception = assertThrows(ArgumentNullException.class, () -> registerBusiness.register(null, speaker));
        assertEquals("Email is required.", exception.getMessage());
    }

    @Test
    @DisplayName("Email ของ speaker ไม่อยู่่ในกลุ่มผู้ให้บริการ จึงเกิด exception ขึ้นมา")
    public void case04() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("chai");
        speaker.setLastName("Foo");
        speaker.setEmail("chai.Foo@fail.com");
        Exception exception = assertThrows(SpeakerDoesntMeetRequirementsException.class, () -> registerBusiness.register(null, speaker));
        assertEquals("Speaker doesn't meet our standard rules.", exception.getMessage());
    }

    @Test
    @DisplayName("exp ของ speaker มีค่าเป็น null จึงเกิด exception ขึ้นมา")
    public void case05() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("chai");
        speaker.setLastName("Foo");
        speaker.setEmail("chai.Foo@gmail.com");
        Exception exception = assertThrows(SaveSpeakerException.class, () -> registerBusiness.register(null, speaker));
        assertEquals("Can't save a speaker.", exception.getMessage());
    }

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