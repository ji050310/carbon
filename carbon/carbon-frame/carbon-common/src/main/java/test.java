import com.carbon.common.feishu.FeiShuAPI;
import com.carbon.domain.mq.entity.AddTradingAccountApproval;
import com.carbon.domain.mq.entity.AssetUploadApproval;

public class test {
    public static void main(String[] args) {
        //创建账号审批实体类
        AddTradingAccountApproval approval = new AddTradingAccountApproval();
        approval.setUserName("zc");
        approval.setAgenciesName("qst");
        approval.setContactNumber("111222333");
        approval.setExchangeName("shanghai Exchange");
        approval.setTradeAccount("ls");
        approval.setRemark("123");
        approval.setAccountProof("123");
        //调用方法发送审批
        FeiShuAPI.addTradingAccountApproval(approval);    }
}
