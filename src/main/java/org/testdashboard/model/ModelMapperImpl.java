package org.testdashboard.model;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.testdashboard.model.Account;
import org.testdashboard.model.AccountDTO;

/**
 * @author Arne
 * @since 21/11/2017
 */
public class ModelMapperImpl {

    private ModelMapperImpl() {}

    public static Account getAccount(AccountDTO accountDTO) {
        return getPreconfiguredMapper().map(accountDTO, Account.class);
    }

    public static AccountDTO getAccountDTO(Account account) {
        return getPreconfiguredMapper().map(account, AccountDTO.class);
    }

    private static ModelMapper getPreconfiguredMapper() {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return mapper;

    }

}
