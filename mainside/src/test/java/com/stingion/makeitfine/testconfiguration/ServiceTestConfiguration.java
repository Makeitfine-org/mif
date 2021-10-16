/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.testconfiguration;

import static com.stingion.makeitfine.testconfiguration.TestConstants.SECURE_RANDOM;

import com.stingion.makeitfine.data.model.Bank;
import com.stingion.makeitfine.data.model.CreditCard;
import com.stingion.makeitfine.data.model.Item;
import com.stingion.makeitfine.data.model.Ordering;
import com.stingion.makeitfine.data.model.UserProfile;
import com.stingion.makeitfine.data.model.payment.Payment;
import com.stingion.makeitfine.data.model.user.Contact;
import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.model.utils.CardType;
import com.stingion.makeitfine.data.model.utils.OrderingStatus;
import com.stingion.makeitfine.data.model.utils.State;
import com.stingion.makeitfine.data.service.model.BankService;
import com.stingion.makeitfine.data.service.model.ContactService;
import com.stingion.makeitfine.data.service.model.CreditCardService;
import com.stingion.makeitfine.data.service.model.ItemService;
import com.stingion.makeitfine.data.service.model.OrderingService;
import com.stingion.makeitfine.data.service.model.PaymentService;
import com.stingion.makeitfine.data.service.model.UserProfileService;
import com.stingion.makeitfine.data.service.model.UserService;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@TestConfiguration
@ImportResource("classpath:spring-test/context-test.xml")
public class ServiceTestConfiguration {

    @Autowired
    private UserService userService;

    @Autowired
    private BankService bankService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderingService orderingService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserProfileService userProfileService;

    @Bean
    public EntityTestData<User> userTestData() {
        int id = 3;

        User userSaved = new User();
        userSaved.setSsoId("any_sso");
        userSaved.setPassword("any_password");
        userSaved.setEmail("any@email.any");
        userSaved.setState(State.INACTIVE);

        User userUpdated = userService.findById(id);
        userUpdated.setEmail("any_email" + SECURE_RANDOM.nextInt() + "@email.any");

        return new EntityTestData<>(
                id,
                "[Worker(super=Contact(super=User(id=1, ssoId=bill, email=bill@xyz.com, "
                        + "state=Active, userProfiles=[UserProfile(id=1, type=USER)]), firstName=Bill,"
                        + " middleName=null, lastName=Watcher, birthDay=1981-08-08 00:00:00.0, "
                        + "phoneNumber=0735553432)), Worker(super=Contact(super=User(id=2, ssoId=danny, "
                        + "email=danny@xyz.com, state=Active, userProfiles=[UserProfile(id=1, type=USER)]),"
                        + " firstName=Danny, middleName=null, lastName=Theys, birthDay=1986-06-03 00:00:00.0,"
                        + " phoneNumber=0715558440)), Worker(super=Contact(super=User(id=3, ssoId=sam, "
                        + "email=samy@xyz.com, state=Inactive, userProfiles=[UserProfile(id=2, type=ADMIN)]), "
                        + "firstName=Sam, middleName=Middlware, lastName=Smith, birthDay=1982-08-30 00:00:00.0, "
                        + "phoneNumber=0725556495)), Customer(super=Contact(super=User(id=4, ssoId=nicole, "
                        + "email=nicloe@xyz.com, state=Locked, userProfiles=[UserProfile(id=3, type=DBA)]),"
                        + " firstName=Nicole, middleName=null, lastName=Warner, birthDay=1987-05-22 00:00:00.0,"
                        + " phoneNumber=null)), Worker(super=Contact(super=User(id=5, ssoId=kenny, "
                        + "email=kenny@xyz.com, state=Inactive, userProfiles=[UserProfile(id=2, type=ADMIN), "
                        + "UserProfile(id=3, type=DBA)]), firstName=Kenny, middleName=Kotton, lastName=Roger,"
                        + " birthDay=1980-08-29 00:00:00.0, phoneNumber=0725137211)), "
                        + "Customer(super=Contact(super=User(id=6, ssoId=sudo, "
                        + "email=sudo@xxx.xxx, state=Active, userProfiles=[UserProfile(id=1, type=USER),"
                        + " UserProfile(id=2, type=ADMIN), UserProfile(id=3, type=DBA)]), firstName=SUDO, "
                        + "middleName=null, lastName=SUDO, birthDay=1979-12-28 00:00:00.0, phoneNumber=null)), "
                        + "Customer(super=Contact(super=User(id=7, ssoId=tsuser, email=tsuser@xxx.xxx, "
                        + "state=Active, userProfiles=[UserProfile(id=1, type=USER)]), firstName=Testside, "
                        + "middleName=null, lastName=Userino, birthDay=1978-04-03 00:00:00.0, phoneNumber=0715554032)),"
                        + " Worker(super=Contact(super=User(id=8, ssoId=admin, email=admin@xxx.xxx, state=Active, "
                        + "userProfiles=[UserProfile(id=2, type=ADMIN)]), firstName=Admin, middleName=null, "
                        + "lastName=Userino, birthDay=1980-08-29 00:00:00.0, phoneNumber=0725557763))]",
                "Worker(super=Contact(super=User(id=3, ssoId=sam, email=samy@xyz.com, "
                        + "state=Inactive, userProfiles=[UserProfile(id=2, type=ADMIN)]), "
                        + "firstName=Sam, middleName=Middlware, lastName=Smith, "
                        + "birthDay=1982-08-30 00:00:00.0, phoneNumber=0725556495))",
                userSaved,
                userUpdated);
    }

    @Bean
    public EntityTestData<UserProfile> userProfileTestData() {
        int id = 2;

        UserProfile userProfileSaved = new UserProfile();
        userProfileSaved.setType("PERMISSIONLESS");

        UserProfile userProfileUpdated = userProfileService.findById(id);
        userProfileUpdated.setType("FULL");

        return new EntityTestData<>(
                id,
                "[UserProfile(id=1, type=USER), UserProfile(id=2, type=ADMIN), UserProfile(id=3, type=DBA)]",
                "UserProfile(id=2, type=ADMIN)",
                userProfileSaved,
                userProfileUpdated);
    }

    @Bean
    public EntityTestData<Bank> bankTestData() {
        int id = 3;

        Bank bankSaved = new Bank("any bank name");

        Bank bankUpdated = bankService.findById(id);
        bankUpdated.setName("any bank name " + SECURE_RANDOM.nextInt());

        return new EntityTestData<>(
                id,
                "[Bank(id=1, name=Unicredit), Bank(id=2, name=Raiffeisen), "
                        + "Bank(id=3, name=PrivatBank), Bank(id=4, name=UkrSibbank), "
                        + "Bank(id=5, name=Platinum Bank)]",
                "Bank(id=3, name=PrivatBank)",
                bankSaved,
                bankUpdated);
    }

    @Bean
    public EntityTestData<CreditCard> creditCardTestData() {
        int id = 1;

        CreditCard creditCardSaved = new CreditCard();
        creditCardSaved.setNumber(9234222312342222L);
        creditCardSaved.setType(CardType.MasterCard);
        creditCardSaved.setBank(bankService.findById(2));
        creditCardSaved.setContact(contactService.findById(3));

        CreditCard creditCardUpdated = creditCardService.findById(id);
        creditCardUpdated.setNumber(1111000011112222L);

        return new EntityTestData<>(
                id,
                "[CreditCard(id=1, number=1234571277518812, type=MasterCard), "
                        + "CreditCard(id=2, number=6647486058945594, type=Visa), "
                        + "CreditCard(id=3, number=2237435131126577, type=Visa), "
                        + "CreditCard(id=4, number=1123729594138004, type=MasterCard), "
                        + "CreditCard(id=5, number=6541296068298653, type=Visa), "
                        + "CreditCard(id=6, number=5923542550577566, type=MasterCard), "
                        + "CreditCard(id=7, number=7970188838706486, type=Visa), "
                        + "CreditCard(id=8, number=2184747417855136, type=Visa)]",
                "CreditCard(id=1, number=1234571277518812, type=MasterCard)",
                creditCardSaved,
                creditCardUpdated);
    }

    @Bean
    public EntityTestData<Contact> contactTestData() {
        int id = 2;

        Contact contactSaved = new Contact();

        contactSaved.setFirstName("any firstname");
        contactSaved.setLastName("any lastname");
        contactSaved.setBirthDay(new Date());

        contactSaved.setSsoId("any_sso");
        contactSaved.setPassword("any_password");
        contactSaved.setEmail("any@email.any");
        contactSaved.setState(State.DELETED);

        Contact contactUpdated = contactService.findById(id);
        contactUpdated.setBirthDay(new Date());

        return new EntityTestData<>(
                id,
                "[Worker(super=Contact(super=User(id=1, ssoId=bill, email=bill@xyz.com, state=Active, "
                        + "userProfiles=[UserProfile(id=1, type=USER)]), firstName=Bill, middleName=null, "
                        + "lastName=Watcher, birthDay=1981-08-08 00:00:00.0, phoneNumber=0735553432)), "
                        + "Worker(super=Contact(super=User(id=2, ssoId=danny, email=danny@xyz.com, state=Active, "
                        + "userProfiles=[UserProfile(id=1, type=USER)]), firstName=Danny, middleName=null, "
                        + "lastName=Theys, birthDay=1986-06-03 00:00:00.0, phoneNumber=0715558440)), "
                        + "Worker(super=Contact(super=User(id=3, ssoId=sam, email=samy@xyz.com, state=Inactive, "
                        + "userProfiles=[UserProfile(id=2, type=ADMIN)]), firstName=Sam, middleName=Middlware, "
                        + "lastName=Smith, birthDay=1982-08-30 00:00:00.0, phoneNumber=0725556495)), "
                        + "Customer(super=Contact(super=User(id=4, ssoId=nicole, email=nicloe@xyz.com, "
                        + "state=Locked, userProfiles=[UserProfile(id=3, type=DBA)]), firstName=Nicole, "
                        + "middleName=null, lastName=Warner, birthDay=1987-05-22 00:00:00.0, phoneNumber=null)),"
                        + " Worker(super=Contact(super=User(id=5, ssoId=kenny, email=kenny@xyz.com, state=Inactive,"
                        + " userProfiles=[UserProfile(id=2, type=ADMIN), UserProfile(id=3, type=DBA)]), "
                        + "firstName=Kenny, middleName=Kotton, lastName=Roger, birthDay=1980-08-29 00:00:00.0, "
                        + "phoneNumber=0725137211)), Customer(super=Contact(super=User(id=6, ssoId=sudo, "
                        + "email=sudo@xxx.xxx, state=Active, "
                        + "userProfiles=[UserProfile(id=1, type=USER), UserProfile(id=2, type=ADMIN), "
                        + "UserProfile(id=3, type=DBA)]), firstName=SUDO, middleName=null, lastName=SUDO, "
                        + "birthDay=1979-12-28 00:00:00.0, phoneNumber=null)), Customer(super=Contact(super=User(id=7, "
                        + "ssoId=tsuser, email=tsuser@xxx.xxx, state=Active, userProfiles=[UserProfile(id=1, "
                        + "type=USER)]), firstName=Testside, middleName=null, lastName=Userino, birthDay=1978-04-03 "
                        + "00:00:00.0, phoneNumber=0715554032)), Worker(super=Contact(super=User(id=8, ssoId=admin, "
                        + "email=admin@xxx.xxx, state=Active, userProfiles=[UserProfile(id=2, type=ADMIN)]), "
                        + "firstName=Admin, middleName=null, "
                        + "lastName=Userino, birthDay=1980-08-29 00:00:00.0, phoneNumber=0725557763))]",
                "Worker(super=Contact(super=User(id=2, ssoId=danny, email=danny@xyz.com, state=Active,"
                        + " userProfiles=[UserProfile(id=1, type=USER)]), firstName=Danny, middleName=null, "
                        + "lastName=Theys, birthDay=1986-06-03 00:00:00.0, phoneNumber=0715558440))",
                contactSaved,
                contactUpdated);
    }

    @Bean
    public EntityTestData<Item> itemTestData() {
        int id = 3;

        Item itemSaved = new Item("some item", 1.33);

        Item itemUpdated = itemService.findById(id);
        itemUpdated.setHeader("some new header" + SECURE_RANDOM.nextInt());

        return new EntityTestData<>(
                id,
                "[Item(id=1, header=Repair room, price=100.0), Item(id=2, header=Repair bathroom, price=150.0), "
                        + "Item(id=3, header=Build garage, price=1500.0), Item(id=4, header=Repair tap, price=25.5), "
                        + "Item(id=5, header=Install plumber, price=40.0)]",
                "Item(id=3, header=Build garage, price=1500.0)",
                itemSaved,
                itemUpdated);
    }

    @Bean
    public EntityTestData<Ordering> orderingTestData() {
        int id = 2;

        Ordering orderingSaved = new Ordering();
        orderingSaved.setStatus(OrderingStatus.Pending);
        orderingSaved.setCreditCard(creditCardService.findById(2));
        orderingSaved.setDescription("Any description");
        orderingSaved.setItem(itemService.findById(3));

        Ordering orderingUpdated = orderingService.findById(id);
        orderingUpdated.setStatus(OrderingStatus.Cancelled);

        return new EntityTestData<>(
                id,
                "[Ordering(id=1, description=Deliver to Kyiv, Melnyka 35/5, status=Pending), Ordering(id=2, "
                        + "description=Deliver to Myshkova, Stynova 15/15 after 18:00, status=Pending), Ordering(id=3, "
                        + "description=Deliver to Kyiv, Strilsova 11/11, status=Pending), Ordering(id=4, "
                        + "description=Cancelled cause of subjective reason, status=Cancelled), Ordering(id=5, "
                        + "description=Delivered to Kyiv, Smirnova 25/101, status=Performed)]",
                "Ordering(id=2, description=Deliver to Myshkova, Stynova 15/15 after 18:00, status=Pending)",
                orderingSaved,
                orderingUpdated);
    }

    @Bean
    public EntityTestData<Payment> paymentEntityTestData() {
        int id = 2;

        Payment paymentSaved = new Payment();
        paymentSaved.setAmount(222.33D);

        Payment paymentUpdated = paymentService.findById(id);
        paymentUpdated.setAmount(0D);

        return new EntityTestData<>(
                id,
                "[Cash(super=Payment(id=1, amount=234.3)), Payment(id=2, amount=62343.31), "
                        + "Card(super=Payment(id=3, amount=1234.0))]",
                "Payment(id=2, amount=62343.31)",
                paymentSaved,
                paymentUpdated);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter(onMethod_ = {@NonNull})
    @Setter(onParam_ = {@NonNull})
    @EqualsAndHashCode
    @ToString
    public static class EntityTestData<T> {

        /**
         * Id for findById, delete, update checking.
         */
        private int id;

        private String findAll;
        private String findById;
        private T savedEntity;
        private T updateEntity;
    }
}
