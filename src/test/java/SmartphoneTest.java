import contacts.BusinessContact;
import contacts.Contact;
import contacts.Friend;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.*;

class SmartphoneTest {

    @Nested
    class TestContactMethods {

        static Contact contactFriendOne;
        static Contact contactFriendTwo;
        static Contact contactBusinessOne;
        static Contact contactBusinessTwo;
        static Contact contactNull;
        static Smartphone smartphone;



        static void contactMethodsSetup() {
            contactFriendOne = new Friend("Daniel", "017375836485");
            contactFriendTwo = new Friend("Madlen", "016747596748");
            contactBusinessOne = new BusinessContact("Cara", "Mobis");
            contactBusinessTwo = new BusinessContact("Romina", "Eventim");
            contactNull = null;
            List<Contact> contactList = new ArrayList<>();
            smartphone = new Smartphone("IPhone", "Apple", contactList);

            smartphone.addContact(contactFriendOne);
            smartphone.addContact(contactBusinessOne);
            smartphone.addContact(contactNull);
            smartphone.addContact(contactFriendTwo);
            smartphone.addContact(contactBusinessTwo);

        }

        @Test
        void addContact() {
            contactMethodsSetup();
            thenList(smartphone.getContacts())
                    .contains(contactBusinessOne,contactBusinessTwo,contactFriendOne,contactFriendTwo)
                    .doesNotContain(contactNull)
                    .isExactlyInstanceOf(ArrayList.class);
        }



        @ParameterizedTest
        @MethodSource
        void getContact(int index, Contact contact) {
            then(smartphone.getContact(index)).isEqualTo(contact).isNotNull();
        }

        @TestFactory
        private static Stream<Arguments> getContact() {
            contactMethodsSetup();
            System.out.println(contactBusinessOne);
            return Stream.of(
                    Arguments.of(0, contactFriendOne),
                    Arguments.of(1, contactBusinessOne),
                    Arguments.of(2, contactFriendTwo),
                    Arguments.of(3, contactBusinessTwo)
            );
        }

        @ParameterizedTest
        @MethodSource
        void getContactByName(String name, Contact contact) {
            contactMethodsSetup();
            assertThat(smartphone.getContactByName(name))
                    .as("Checking to find %s", name)
                    .isEqualTo(contact);
        }

        @Test
        void getContactByName_ThrowsIllegalStateException() {
            //Given
            contactMethodsSetup();
            String nameDoesNotExist = "Danie";

            //When
            Throwable throwable = catchThrowable(() -> smartphone.getContactByName(nameDoesNotExist));

            then(throwable).as("IllegalStateException should be thrown when name is not found")
                    .isInstanceOf(IllegalStateException.class)
                    .as("Has message containing input name")
                    .hasMessageContaining(nameDoesNotExist);
        }

        private static Stream<Arguments> getContactByName() {
            contactMethodsSetup();
            return Stream.of(
                    Arguments.of("Daniel", contactFriendOne),
                    Arguments.of("Madlen", contactFriendTwo),
                    Arguments.of("Cara", contactBusinessOne),
                    Arguments.of("Romina", contactBusinessTwo)
            );
        }



        @Test
        void removeContactByName() {
        }


    }

    @Nested
    class TestInterfaceMethods {


        @Test
        void getPosition() {
        }

        @Test
        void startRadio() {
        }

        @Test
        void stopRadio() {
        }

    }

}