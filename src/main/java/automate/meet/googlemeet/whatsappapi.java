package automate.meet.googlemeet;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class whatsappapi{

    public static final String ACC_SID="";
    public static final String AUTH_TOK="";

    @RequestMapping(value="api/whatsapp")
    public static void main(String[] args){
        Twilio.init(ACC_SID, AUTH_TOK);

        Message msg=Message.creator(new PhoneNumber("whatsapp:+91mobilenumber"), new PhoneNumber("whatsapp:+14155238886"), "THIS IS SOMETHING TO UNDERSTAND").create();
        ResourceSet<Message> message=Message.reader().read();
        System.out.println(msg.getSid());
        for(Message mess:message){
            System.out.println(mess.getBody());
        }
    }
}