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
 * 1) verify on below:
 *    - Topup Amount
 *    - Deposit Type = Bank Deposit
 *    - Deposit Into
 *      - Bank
 *      - Bank Account
 *      - Bank Address
 *    - Status = New
 * 2) Confirm on the deposit, verify:
 *    - the Remark field is mandatory
 */

amount = WebUI.getText(findTestObject('SY/Transaction/Pending Deposit/column_topupAmount'))

type = WebUI.getText(findTestObject('SY/Transaction/Pending Deposit/column_depositType'))

info = WebUI.getText(findTestObject('SY/Transaction/Pending Deposit/column_depositInfo'))

status = WebUI.getText(findTestObject('Object Repository/SY/Transaction/Pending Deposit/column_status'))

println('amount :: ' + amount)

println('type :: ' + type)

println('info :: ' + info)

println('status :: ' + status)

'status = "New Lock" -> "New" '
status = status.substring(0, 3)

WebUI.verifyMatch(status, "New", false)

double dAmount = new Double(amount.toString());

int amount = (int)dAmount;

stamount = amount.toString()

println("stamount :: " + stamount)

WebUI.verifyMatch(stamount, GlobalVariable.reloadAmount, false)

WebUI.verifyMatch(type, "Bank Deposit", false)

'if all the info correct, proceed to approve the pending deposit'
if(info.contains(GlobalVariable.BankName) && info.contains(GlobalVariable.BankAddress) && info.contains(GlobalVariable.BankAccount)) {
	WebUI.delay(1)
	
	WebUI.click(findTestObject('Object Repository/SY/Transaction/Pending Deposit/column_action'))
	
	WebUI.delay(1)
	
	orderNo = WebUI.getAttribute(findTestObject('Object Repository/SY/Transaction/Pending Deposit/Deposit Verfication Page/text_orderNo'), 'value')
	
	WebUI.verifyMatch(orderNo, GlobalVariable.TrxIDNo, false)
	
	WebUI.delay(1)
	
	member = WebUI.getAttribute(findTestObject('Object Repository/SY/Transaction/Pending Deposit/Deposit Verfication Page/text_member'), 'value')
	
	WebUI.verifyMatch(member, userLogin, false)
	
	WebUI.delay(1)
	
	amount2 = WebUI.getAttribute(findTestObject('Object Repository/SY/Transaction/Pending Deposit/Deposit Verfication Page/text_amount'), 'value')
	
	println("amount :: " + amount2)
	
	double dAmount2 = new Double(amount2.toString());
	
	int amount2 = (int)dAmount2;
	
	strAmount = amount2.toString()
	
	WebUI.verifyMatch(strAmount, GlobalVariable.reloadAmount, false)
	
	bankName = WebUI.getAttribute(findTestObject('Object Repository/SY/Transaction/Pending Deposit/Deposit Verfication Page/text_bankName'), 'value')
	
	println('Bank Name :: ' + bankName)
	
	WebUI.verifyMatch(bankName, GlobalVariable.BankName, false)
	
	verifyRadio = WebUI.getText(findTestObject('Object Repository/SY/Transaction/Pending Deposit/Deposit Verfication Page/radio_verify'))
	
	println('verifyRadio :: ' + verifyRadio)
	
	WebUI.verifyMatch(verifyRadio, "Approve", false)
	
	WebUI.click(findTestObject('SY/Transaction/Pending Deposit/btn_confirm'))
	
	WebUI.delay(1)
	
	WebUI.verifyElementPresent(findTestObject('SY/Transaction/Pending Deposit/warning_remarkRequired'), 2)
	
	WebUI.delay(1)
	
	WebUI.setText(findTestObject('SY/Transaction/Pending Deposit/text_remark'), 'Deposit Approved')
	
	WebUI.delay(1)
	
	WebUI.click(findTestObject('SY/Transaction/Pending Deposit/btn_confirm'))
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/SY/Transaction/Pending Deposit/message_updateDepositDone'), 3)
	
	WebUI.delay(1)
}
