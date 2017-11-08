package com.platform.parent.request.financial;

import java.util.List;

public class TransactionSettlementReq {
    private List<SettlementReq> settlementReqList;

    public TransactionSettlementReq()
    {
    }

    public TransactionSettlementReq(List<SettlementReq> settlementReqList)
    {
        this.settlementReqList = settlementReqList;
    }

    public List<SettlementReq> getSettlementReqList()
    {
        return settlementReqList;
    }

    public void setSettlementReqList(List<SettlementReq> settlementReqList)
    {
        this.settlementReqList = settlementReqList;
    }
}
