import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

/*
 * 1) verify on the url, ensure browser is directed to the history page
 * 2) verify on below:
 *    - Transaction type = Bank Deposit
 *    - Amount
 *    - Status = Pending
 */

WebUI.refresh()

url = WebUI.getUrl()

println("URL :: " + url)

if(url.contains('user/records/deposit')) {
	
	idNo = WebUI.getText(findTestObject('Object Repository/Zi-O/Transaction/History/tableRecord_first_idNumber'))
	
	println('idNo :: ' + idNo)
	
	GlobalVariable.TrxIDNo = idNo
	
	trxType = WebUI.getText(findTestObject('Object Repository/Zi-O/Transaction/History/tableRecord_first_trxType'))
	
	WebUI.verifyMatch(trxType, "Bank Deposit", false)
	
	trxAmount = WebUI.getText(findTestObject('Object Repository/Zi-O/Transaction/History/tableRecord_first_amount'))
	
	WebUI.verifyMatch(trxAmount, GlobalVariable.reloadAmount + ".00", false)
	
	trxStatus = WebUI.getText(findTestObject('Object Repository/Zi-O/Transaction/History/tableRecord_first_status'))
	
	println(trxStatus)
	
	WebUI.verifyMatch(trxStatus, "PENDING", false)
	
	WebUI.delay(1)
	
}