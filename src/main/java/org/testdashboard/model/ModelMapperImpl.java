package org.testdashboard.model;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.testdashboard.model.Account;
import org.testdashboard.model.AccountDTO;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

/**
 * @author Arne
 * @since 21/11/2017
 */
public class ModelMapperImpl {

    private ModelMapperImpl() {}

    /*
     * Account
     */

    public static Account getAccount(AccountDTO accountDTO) {
        return getPreconfiguredMapper().map(accountDTO, Account.class);
    }

    public static AccountDTO getAccountDTO(Account account) {
        return getPreconfiguredMapper().map(account, AccountDTO.class);
    }

    /*
     * Project
     */

    public static ProjectDTO getProjectDTO(Project project) {
        return getPreconfiguredMapper().map(project, ProjectDTO.class);
    }

    public static List<ProjectDTO> getProjectDTOs(List<Project> projects) {

        Type targetListType = new TypeToken<List<ProjectDTO>>() {}.getType();
        List<ProjectDTO> projectDTOs = getPreconfiguredMapper().map(projects, targetListType);
        return projectDTOs;

    }

    /*
     * Configuration
     */

    private static ModelMapper getPreconfiguredMapper() {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return mapper;

    }

}
