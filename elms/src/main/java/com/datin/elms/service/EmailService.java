package com.datin.elms.service;


import com.datin.elms.model.Email;
import com.datin.elms.model.Attachment;
import com.datin.elms.model.Employee;
import com.datin.elms.repository.CategoryDao;
import com.datin.elms.repository.EmailDao;
import com.datin.elms.util.HibernateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

public class EmailService {
    static Logger log = Logger.getLogger(EmailService.class.getName());
    EmailDao emailDao = new EmailDao();

    public void deleteEmail(int emailId) {
        BasicConfigurator.configure();
        if (emailDao.deleteEmailById(emailId))
            log.info("Email deleted successfully...");
        else log.warn("Email could not be deleted...");

    }

    public Attachment getEmailAttachment(int emailId) {
        BasicConfigurator.configure();

        Email email = emailDao.getEmailById(emailId) ;
        Attachment attachment =  emailDao.downloadAttachment(email) ;

        if (attachment != null) {
            log.info("Attachment loaded Successfully ...");
            return attachment;
        } else {
            log.warn("Attachment couldn't load ...");
            return null ;
        }

    }

    public List<Email> getInboxEmails (Employee employee) {
        BasicConfigurator.configure();
        List<Email> inbox = emailDao.getEmailByReceiver(employee.getEmail());

            log.info("number of emails By receiver EmailAddress is : " + inbox.size() );
            return inbox ;
    }

    public List<Email> getSentEmails (Employee employee) {
        BasicConfigurator.configure();
        List<Email> sent = emailDao.getEmailBySender(employee.getEmail());

        log.info("number of emails By sender EmailAddress is : "+sent.size());
        return sent ;

    }

    public void sendEmail (Employee employee ,String subject , String receiver , String content , Part filePart) throws IOException {
        BasicConfigurator.configure();

        Email email = new Email();

        email.setSubject(subject);
        email.setEmail_receiver(receiver);
        email.setContent(content);
        email.setStatus(CategoryDao.getElementByName("unread"));
        email.setEmail_sender(employee.getEmail());

        if (filePart.getSize() > 0) {
            log.info("Email contains Attachment ...");
            email.setAttachment(true);
            if (emailDao.save(email) ) {
                log.info("Email saved successfully ...");
            }else {
                log.warn("Email Couldn't be saved ...");
            }

            InputStream inputStream = null;
            inputStream = filePart.getInputStream();
            File targetFile = new File("src/main/resources/targetFile.tmp");
            FileUtils.copyInputStreamToFile(inputStream, targetFile);
            FileInputStream fis = new FileInputStream(targetFile);
            Blob data = HibernateUtil.getHibernateSession().getLobHelper().createBlob(fis, filePart.getSize());
            Attachment attachment = new Attachment();
            attachment.setFileName("attachment");
            attachment.setFileType(filePart.getContentType());
            attachment.setData(data);
            attachment.setEmail(email);

            if ( emailDao.saveEmailAttachment(attachment)) {
                log.info("Attachment saved successfully ...");
            } else {
                log.warn("Attachment couldn't be saved ...");
            }

            inputStream.close();
        } else {
            email.setAttachment(false);
            emailDao.save(email);
        }


    }

    public Email viewEmail (int emailId) {
        BasicConfigurator.configure();

        Email email = emailDao.getEmailById(emailId);

        if ( emailDao.updateStatus(email) ) {
            log.info("Email Viewed and status changed ...");
        } else {
            log.warn("The status of email couldn't to be changed ");
        }

        return email ;
    }


}
