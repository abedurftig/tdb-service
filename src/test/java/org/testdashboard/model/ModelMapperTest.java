package org.testdashboard.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.testdashboard.model.Account;
import org.testdashboard.model.AccountDTO;
import org.testdashboard.model.ModelMapperImpl;

/**
 * @author Arne
 * @since 20/11/2017
 */
public class ModelMapperTest {

    @Test
    public void accountModelToDto() {

        // given
        Account account = new Account("Test Account");

        // when
        AccountDTO dto = ModelMapperImpl.getAccountDTO(account);

        // then
        Assert.assertThat(dto.getAccountName(), Matchers.is("Test Account"));

    }

    @Test
    public void accountDtoToModel() {

        // given
        AccountDTO accountDto = new AccountDTO();
        accountDto.setId(123L);
        accountDto.setAccountName("Test Account");

        // when
        Account account = ModelMapperImpl.getAccount(accountDto);

        // then
        Assert.assertThat(account.getName(), Matchers.is("Test Account"));
        Assert.assertThat(account.getId(), Matchers.is(123L));

    }

}
