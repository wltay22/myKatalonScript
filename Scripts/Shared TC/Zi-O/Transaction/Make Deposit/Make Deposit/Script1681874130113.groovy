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
 *    - the transaction type: [DEPOSIT] label
 *    - bank name
 *    - bank account
 *    - name
 * 2) make deposit, verify:
 *    - the reload amount
 *    - the "Others amount"
 * 3) upload receipt, verify the receipt is uploaded successfully
 */

String userDir = System.getProperty("user.dir")
String receiptPath = userDir + "\\UploadFiles\\receipt.png"

WebUI.click(findTestObject('Object Repository/Zi-O/Transaction/Make Deposit/btn_deposit'))

WebUI.delay(1)

WebUI.verifyElementPresent(findTestObject('Object Repository/Zi-O/Transaction/Make Deposit/label_deposit'), 2)

WebUI.delay(1)

bname = WebUI.getText(findTestObject('Zi-O/Transaction/Make Deposit/label_bankName'))

bAccount = WebUI.getText(findTestObject('Zi-O/Transaction/Make Deposit/label_bankAccount'))

name = WebUI.getText(findTestObject('Zi-O/Transaction/Make Deposit/label_name'))

println('bname :: ' + bname)

println('bAccount :: ' + bAccount)

println('name :: ' + name)

WebUI.verifyMatch(bname, GlobalVariable.BankName, false)

WebUI.verifyMatch(bAccount, GlobalVariable.BankAccount, false)

WebUI.verifyMatch(name, GlobalVariable.BankAddress, false)

WebUI.click(findTestObject('Object Repository/Zi-O/Transaction/Make Deposit/btn_50'))

WebUI.delay(1)

readAmount = WebUI.getAttribute(findTestObject('Object Repository/Zi-O/Transaction/Make Deposit/label_readonly_amount'), 
    'value')

otherAmount = WebUI.getAttribute(findTestObject('Object Repository/Zi-O/Transaction/Make Deposit/label_othersAmount'), 'value')

println('readAmount :: ' + readAmount)

println('otherAmount :: ' + otherAmount)

WebUI.verifyMatch(readAmount, GlobalVariable.reloadAmount, false)

WebUI.verifyMatch(otherAmount, GlobalVariable.reloadAmount, false)

WebUI.delay(1)

println("receiptPath :: " + receiptPath)

//WebUI.uploadFile(findTestObject('Zi-O/Transaction/Make Deposit/uploadReceipt'), GlobalVariable.receiptPath)
WebUI.uploadFile(findTestObject('Zi-O/Transaction/Make Deposit/uploadReceipt'), receiptPath)

WebUI.delay(1)

uploadedReceipt = WebUI.getText(findTestObject('Zi-O/Transaction/Make Deposit/label_uploadedReceipt'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyNotMatch(uploadedReceipt, '', false)

WebUI.click(findTestObject('Object Repository/Zi-O/Transaction/Make Deposit/btn_submit'))

WebUI.verifyElementPresent(findTestObject('Zi-O/Transaction/Make Deposit/message_successfullySubmitted'), 2)

WebUI.delay(3)

