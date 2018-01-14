package org.tdb.api.json;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import org.tdb.model.Account;

@JsonRpcService("/rpc/account")
public interface AccountService {

    Account createAccount (@JsonRpcParam(value = "accountName") String accountName,
                           @JsonRpcParam(value = "userName") String userName,
                           @JsonRpcParam(value = "password") String password);

}


