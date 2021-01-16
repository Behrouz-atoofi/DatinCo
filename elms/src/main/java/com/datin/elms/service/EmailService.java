package com.datin.elms.service;


import com.datin.elms.model.Attachment;
import com.datin.elms.model.CategoryElement;
import com.datin.elms.model.Email;
import com.datin.elms.model.Employee;
import com.datin.elms.repository.CategoryDao;
import com.datin.elms.repository.EmailDao;
import com.datin.elms.repository.EmployeeDao;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EmailService {
    static Logger log = Logger.getLogger(EmailService.class.getName());
    EmailDao emailDao = new EmailDao();
    EmployeeDao employeeDao = new EmployeeDao();

    public void deleteEmail(int emailId) {
        BasicConfigurator.configure();
        if (emailDao.deleteEmailById(emailId))
            log.info("Email deleted successfully...");
        else log.warn("Email could not be deleted...");

    }

    public List<Attachment> getEmailAttachments(int emailId) {
        BasicConfigurator.configure();

        Email email = emailDao.getEmailById(emailId);
        List<Attachment> attachments = emailDao.downloadAttachments(email);

        if (attachments != null) {
            log.info("Attachment loaded Successfully ...");
            return attachments;
        } else {
            log.warn("Attachment couldn't load ...");
            return null;
        }

    }

    public Attachment getAttachmentById(int attachmentId) {


        BasicConfigurator.configure();

        Attachment attachment = emailDao.downloadSingleAttachment(attachmentId);

        if (attachment != null) {
            log.info("Attachment loaded Successfully ...");
            return attachment;
        } else {
            log.warn("Attachment couldn't load ...");
            return null;
        }

    }

    public List<Email> getInboxEmails(Employee receiver) {
        BasicConfigurator.configure();
        List<Email> inbox = emailDao.getEmailByReceiver(receiver);

        log.info("number of emails By receiver EmailAddress is : " + inbox.size());
        return inbox;
    }

    public List<Email> getSentEmails(Employee employee) {
        BasicConfigurator.configure();
        List<Email> sent = emailDao.getEmailBySender(employee);

        log.info("number of emails By sender EmailAddress is : " + sent.size());
        return sent;

    }

    public void sendEmail(Employee employee, String subject, String receiversEmail, String content, List<Part> fileParts) throws IOException {
        BasicConfigurator.configure();
        Date dTime = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss a");
        String date_created = df.format(dTime);

        Email email = new Email();
        Employee receiverEmployee;
        String[] receivers = receiversEmail.split(",");

        for (String receiverEmail : receivers) {
            receiverEmployee = employeeDao.getEmployeeByEmail(receiverEmail);
            email.getReceivers().add(receiverEmployee);
        }


        email.setSubject(subject);
        email.setContent(content);
        email.setStatus(CategoryDao.getElementByName("unread"));
        email.setSender(employee);
        email.setDisabled(false);
        email.setDate_created(date_created);
        email.setLast_modified(date_created);

        if (fileParts.size() > 0) {
            log.info("Email contains Attachment ...");
            emailDao.save(email);

            for (Part filePart : fileParts) {
                InputStream inputStream = null;
                inputStream = filePart.getInputStream();
                File targetFile = new File("src/main/resources/targetFile.tmp");
                FileUtils.copyInputStreamToFile(inputStream, targetFile);
                FileInputStream fis = new FileInputStream(targetFile);
                Blob data = HibernateUtil.getHibernateSession().getLobHelper().createBlob(fis, filePart.getSize());
                Attachment attachment = new Attachment();
                attachment.setFileName("attachment");
                attachment.setData(data);
                attachment.setEmail(email);
                attachment.setDate_created(date_created);
                attachment.setLast_modified(date_created);

                CategoryElement fileType = new CategoryElement();

                if (filePart.getContentType().contains("text")) {
                    attachment.setFileType(CategoryDao.getElementByName("text"));
                } else if (filePart.getContentType().contains("image")) {
                    attachment.setFileType(CategoryDao.getElementByName("image"));
                } else if (filePart.getContentType().contains("application/pdf")) {
                    attachment.setFileType(CategoryDao.getElementByName("pdf"));
                }

                if (emailDao.saveEmailAttachment(attachment)) {
                    log.info("Attachment saved successfully ...");
                } else {
                    log.warn("Attachment couldn't be saved ...");
                }
                inputStream.close();
            }

        } else {
            emailDao.save(email);
        }


    }

    public Email viewEmail(int emailId) {
        BasicConfigurator.configure();

        Email email = emailDao.getEmailById(emailId);

        if (emailDao.updateStatus(email)) {
            log.info("Email Viewed and status changed ...");
        } else {
            log.warn("The status of email couldn't to be changed ");
        }

        return email;
    }


}
