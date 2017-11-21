package org.testdashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.testdashboard.model.Account;
import org.testdashboard.model.AccountRepository;
import org.testdashboard.model.Project;
import org.testdashboard.model.ProjectRepository;
import org.testdashboard.storage.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "org.addictedcoders", "org.addictedcoders.api", "org.addictedcoders.conf" })
@EnableConfigurationProperties(StorageProperties.class)
public class Application implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void run(String... arg0) throws Exception {

        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }

        Account account = new Account("Account One");

        Project projectOne = new Project(account, "Project One");
        Project projectTwo = new Project(account, "Project Two");

        account.addToProjects(projectOne);
        account.addToProjects(projectTwo);

        accountRepository.save(account);

        projectRepository.save(projectOne);
        projectRepository.save(projectTwo);

    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Application.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}