package com.platform.parent.request.financial;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SettlementReqList
{
    @Valid
    @NotNull
    private List<SettlementReq> settlementReqList;

    public List<SettlementReq> getSettlementReqList() {
        return settlementReqList;
    }

    public void setSettlementReqList(List<SettlementReq> settlementReqList) {
        this.settlementReqList = settlementReqList;
    }
}
