package com.cts.gsd.user;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.gsd.exception.UserException;
import com.cts.gsd.model.EmailScheduler;
import com.cts.gsd.model.FileDb;
import com.cts.gsd.repository.EmailSchedulerRepository;
import com.cts.gsd.repository.EmailTemplateRepository;
import com.cts.gsd.repository.FileDbRepository;
import com.cts.gsd.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private ThreadPoolTaskScheduler taskScheduler;
	
	@Autowired
	private EmailTemplateRepository templateRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FileDbRepository filerepository;
	
	@Autowired
	private EmailSchedulerRepository emailSchedulerRepository;
		
	public void sendMail() throws UserException{
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo("arunbalaji00@gmail.com");
		message.setText(templateRepository.getContent());
		message.setSubject(templateRepository.getSubject());
		mailSender.send(message);
		
		System.out.println("Mail sent successfully");
	}
	
	public void sendMailWithAttachment(@RequestParam int id) throws MessagingException {
		FileDb file=filerepository.findByName("download.jfif");
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
//		helper.setTo(userRepository.managerEmailid(id));
		helper.setCc(userRepository.getEmailid(id));
		helper.setSubject(templateRepository.getSubject());
		helper.setText(templateRepository.getContent());
		ByteArrayDataSource source=new ByteArrayDataSource(file.getData(), "application/octet-stream");
		helper.addAttachment(file.getName(),source);
		mailSender.send(message);
	}
//	@Scheduled(cron = "*/10 * * * * *")
	public void singleMailScheduler() throws MessagingException {
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		helper.setTo("arunbalaji00@gmail.com");
		helper.setSubject(templateRepository.getSubject());
		helper.setText(templateRepository.getContent());
		mailSender.send(message);
	}
	
//	@PostConstruct
//	public void mailSchedulerDB() throws MessagingException {
//		 EmailScheduler scheduler=emailSchedulerRepository.findById("minutes").orElse(null);
//		
//		if(scheduler!= null) {
//			String cron=scheduler.getCronExpression();
//			taskScheduler.schedule(()->{
//				try {
//					singleMailScheduler();
//				} catch (MessagingException e) {
//					e.printStackTrace();
//				}
//			},new CronTrigger(cron));
//		}
//	}
//	@PostConstruct
//	 public void multipleMailScheduler() throws MessagingException{
//		 EmailScheduler scheduler=emailSchedulerRepository.findById("seconds").orElse(null);
//		 ExecutorService executorService=Executors.newFixedThreadPool(2);
//		 if(scheduler!= null) {
//				String cron=scheduler.getCronExpression();
//				taskScheduler.schedule(()->{
//					try {
//						executorService.execute(sendMail());
//						executorService.execute(sendMailWithAttachment());
//					} catch (MessagingException e) {
//						e.printStackTrace();
//					}
//				},new CronTrigger(cron));
//			}
//	 }

	public Runnable multiThreadMailAttachment() throws MessagingException {
		FileDb file=filerepository.findByName("download.jfif");
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		helper.setTo("arunbalaji00@gmail.com");
		helper.setSubject(templateRepository.getSubject());
		helper.setText(templateRepository.getContent());
		ByteArrayDataSource source=new ByteArrayDataSource(file.getData(), "application/octet-stream");
		helper.addAttachment(file.getName(),source);
		mailSender.send(message);
		return null;
		
	}
	
	public Runnable multiThreadMail() throws MessagingException{
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo("arunbalaji00@gmail.com");
		message.setText(templateRepository.getContent());
		message.setSubject(templateRepository.getSubject());
		mailSender.send(message);
		
		System.out.println("Mail sent successfully");
		return null;
	}
	
	public void multiThread() throws MessagingException{
	
		 ExecutorService executorService=Executors.newFixedThreadPool(2);
						executorService.execute(() -> {
							try {
								multiThreadMailAttachment();
							} catch (MessagingException e) {
								e.printStackTrace();
							}
						});
							executorService.execute(() -> {
							try {
								multiThreadMail();
							} catch (MessagingException e) {
								e.printStackTrace();
							}
						
						});		
						executorService.shutdown();
	 }
	
}