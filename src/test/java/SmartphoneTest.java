import contacts.BusinessContact;
import contacts.Contact;
import contacts.Friend;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
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
            List<Contact> contactList = Stream.of(
                    contactFriendOne,
                    contactFriendTwo,
                    contactNull,
                    contactBusinessOne
            ).toList();
            smartphone = new Smartphone(contactList);
        }



        @Test
        void addContact() {
            contactMethodsSetup();
            smartphone.addContact(contactBusinessTwo);
            thenList(smartphone.getContacts())
                    .contains(contactBusinessOne,contactBusinessTwo,contactFriendOne,contactFriendTwo)
                    .doesNotContain(contactNull)
                    .isInstanceOf(List.class);
        }



        @ParameterizedTest
        @MethodSource
        void getContact(int index, Contact contact) {
            then(smartphone.getContact(index)).isEqualTo(contact).isNotNull();
        }

        private static Stream<Arguments> getContact() {
            contactMethodsSetup();
            System.out.println(contactBusinessOne);
            return Stream.of(
                    Arguments.of(0, contactFriendOne),
                    Arguments.of(1, contactFriendTwo),
                    Arguments.of(2, contactBusinessOne)
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
                    Arguments.of("Cara", contactBusinessOne)
            );
        }



        @Test
        void removeContactByName() {
            contactMethodsSetup();
            smartphone.removeContactByName(contactBusinessOne.getName());

            then(smartphone.getContacts())
                    .contains(contactFriendOne, contactFriendTwo)
                    .doesNotContain(contactBusinessOne,contactBusinessTwo)
                    .doesNotContainNull()
                    .isInstanceOf(List.class);
        }


        @Test
        void removeContactByName_throwsIllegalStateException() {
            contactMethodsSetup();
            String nameDoesNotExist = "Car";
            Throwable throwable = catchThrowable(() -> smartphone.removeContactByName(nameDoesNotExist));

            then(throwable).isInstanceOf(IllegalStateException.class).hasMessageContaining(nameDoesNotExist);
        }


    }

    @Nested
    class TestInterfaceMethods {

        Smartphone smartphone = new Smartphone(Collections.emptyList());

        @Test
        void getPosition() {
            assertThat(smartphone.getPosition()).isEqualTo("Köln");
        }

        @Test
        void startRadio() {
            assertThat(smartphone.startRadio()).isTrue();
        }

        @Test
        void stopRadio() {
            assertThat(smartphone.stopRadio()).isFalse();
        }

    }

}