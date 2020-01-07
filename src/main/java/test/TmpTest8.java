package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TmpTest8 {
	
	public static void main(String[] args) throws Exception {
		
		List<String> l = new ArrayList<String>();
		String str = "/OT.WebSite/bin/OT.CNT.WebController.dll\r\n" + 
				"/OT.WebSite/bin/OT.CNT.WebController.pdb\r\n" + 
				"/OT.WebSite/bin/OT.CNT.ApplicationServices.dll\r\n" + 
				"/OT.WebSite/bin/OT.CNT.ApplicationServices.pdb\r\n" + 
				"/OT.WebSite/bin/OT.CNT.Repository.dll\r\n" + 
				"/OT.WebSite/bin/OT.CNT.Repository.pdb\r\n" + 
				"/OT.WebSite/bin/OT.CNT.IAPIs.dll\r\n" + 
				"/OT.WebSite/bin/OT.CNT.IAPIs.pdb\r\n" + 
				"/OT.WebSite/bin/OT.Workflow.IAPIs.dll\r\n" + 
				"/OT.WebSite/bin/OT.Workflow3.APIs.dll\r\n" + 
				"/OT.WebSite/bin/OT.OPF.dll\r\n" + 
				"/OT.WebSite/bin/OT.OPF.IAPIs.dll\r\n" + 
				"/OT.WebSite/bin/OT.CNT.WebController.dll\r\n" + 
				"/OT.WebSite/Areas/CNT/Views/Contract/ContractArchived/BatchEdit.cshtml\r\n" + 
				"/OT.WebSite/Areas/CNT/Views/Contract/ContractArchived/List.cshtml\r\n" + 
				"/OT.WebSite/Areas/CNT/Views/Contract/Cancel/Details.cshtml\r\n" + 
				"/OT.WebSite/Areas/CNT/Views/Contract/ReceivePayInfo/PaymentApplyEdit.cshtml\r\n" + 
				"/OT.WebSite/Areas/CNT/Views/Contract/End/Details.cshtml\r\n" + 
				"/OT.WebSite/Areas/CNT/Views/Contract/End/Edit.cshtml\r\n" + 
				"/OT.WebSite/Areas/CNT/Views/Contract/End/EndView.cshtml\r\n" + 
				"/OT.WebSite/Areas/CNT/Views/Shared/_ContractPartInfo.cshtml\r\n" + 
				"/OT.WebSite/Areas/CNT/Views/Shared/_Cancling.cshtml\r\n" + 
				"/OT.WebSite/Areas/CNT/Views/Shared/_PaymentApplyDetail.cshtml\r\n" + 
				"/OT.WebSite/Areas/CNT/Views/Home/WarningList.cshtml\r\n" + 
				"/OT.WebSite/Areas/CNT/Reports/ContractEnd.rdlc\r\n" + 
				"/OT.WebSite/Areas/CNT/Reports/ContractChange.rdlc\r\n" + 
				"/OT.WebSite/Areas/CNT/Reports/ContractPayment.rdlc\r\n" + 
				"/OT.WebSite/Areas/CNT/Reports/ContractBalance.rdlc\r\n" + 
				"/OT.WebSite/Areas/CNT/Reports/ContractSign.rdlc\r\n" + 
				"/OT.WebSite/Areas/CNT/Reports/ContractPayment_IsNotCnt.rdlc\r\n" + 
				"/OT.WebSite/Areas/CNT/Views/Base/SignedObject/Edit.cshtml\r\n" + 
				"/OT.WebSite/Areas/CNT/Views/Base/SignedObject/SignedObjectExInfoEdit.cshtml\r\n" + 
				"/OT.WebSite/Areas/CNT/Views/Base/SignedObjectCreditLimit/SignedObjectSelect.cshtml\r\n" + 
				"/OT.WebSite/Views/Shared/SubFlowApprove.cshtml\r\n" + 
				"/OT.WebSite/Images/yizuofei.png"
				;
		l.addAll(Arrays.asList(str.split(System.lineSeparator())));
		Collections.sort(l);
		for(String s : l) {
			System.out.println(s);
		}
	}

}
