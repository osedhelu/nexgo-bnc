## Página 1

API Interface Specification  
Programmer's Reference Manual (Android)  
V- 3.0.4  
2021-08-09  
Shenzhen Xinguodu Technology Co., Ltd Published  
www.xinguodu.com  
Copyright @ 2016-2026 Shenzhen New State Technology Co., Ltd.  
China print and publish  
This document may adjust the technical errors that may be included without notice. Modifications to this  
document will be released in the next release. This document prohibits any form of reproduction or  
propagation without permission.

## Página 2

SmartPos API Reference Manual: 09/08/21  
Version Date Editor Description  
V0.1 2016-08-12 Liu Ting  
V0.2 2016-9-12 Liu Ting Startprint () function to modify the number of  
Parameters  
V0.3 2016-10-12 Zhou Xiaoxin Add the appendix Return Value that is refined  
into the class Description, as well as some  
function changes  
V 0.4 2016-10-31 Zhou Xiaoxin Modify the buzzer interface  
Modify EMV card module power up and down  
from the application layer control  
Modify PIN pad work Cadogan a key type  
V 0.5 2016-11-11 Zhou Xiao Xin Increase silent uninstall Interface  
V 0.6 2016-11-25 Zhou Xin EMV interface modification, the amount of  
correction, whether the use of electronic cash  
callback  
V 0.7 2016-12-05 Zhou Xin Print Increment Print One-dimensional code  
Two-dimensional code interface  
V0.8 2016-12-08 Zhou Xiaoxin Increase the camera scan interface  
V1.0. 4 2017-01-13 Wang Hongyang According to the reference document,  
organize the structure of the document  
V2.0.1 2017-05-04 QiangFang Increase DUKTP  
V2.0.6 2018-01-04 Hassan Add onSetAfterFinalSelectedAppResponse,  
and onAfterFinalSelectedApp method.  
V2.0.7 2018-05-28 Hassan Sync new methods  
V2.0.9 2018-10-29 Hassan 1. OnEmvProcessListener add callback:  
onPrompt, onRemoveCard  
2. EmvHandler add method:  
onSetRemoveCardResponse,  
onSetPromptResponse  
3. Remove armeabi(include  
libnexgo\_emvjni.so, libnexgo\_gencode.so,  
libnexgo\_sdkemvjni.so).  
4. Change SDK package format: jar-aar  
5. CardReader add Felica method:  
setSupportFelica: if support Felica card  
Confidential 2  
| Version | Date | Editor | Description |  
| --- | --- | --- | --- |  
| V0.1 | 2016-08-12 | Liu Ting | |  
| V0.2 | 2016-9-12 | Liu Ting | Startprint () function to modify the number of Parameters |  
| V0.3 | 2016-10-12 | Zhou Xiaoxin | Add the appendix Return Value that is refined into the class Description, as well as some function changes |  
| V 0.4 | 2016-10-31 | Zhou Xiaoxin | Modify the buzzer interface Modify EMV card module power up and down from the application layer control Modify PIN pad work Cadogan a key type |  
| V 0.5 | 2016-11-11 | Zhou Xiao Xin | Increase silent uninstall Interface |  
| V 0.6 | 2016-11-25 | Zhou Xin | EMV interface modification, the amount of correction, whether the use of electronic cash callback |  
| V 0.7 | 2016-12-05 | Zhou Xin | Print Increment Print One-dimensional code Two-dimensional code interface |  
| V0.8 | 2016-12-08 | Zhou Xiaoxin | Increase the camera scan interface |  
| V1.0. 4 | 2017-01-13 | Wang Hongyang | According to the reference document, organize the structure of the document |  
| V2.0.1 | 2017-05-04 | QiangFang | Increase DUKTP |  
| V2.0.6 | 2018-01-04 | Hassan | Add onSetAfterFinalSelectedAppResponse, and onAfterFinalSelectedApp method. |  
| V2.0.7 | 2018-05-28 | Hassan | Sync new methods |  
| V2.0.9 | 2018-10-29 | Hassan | 1. OnEmvProcessListener add callback: onPrompt, onRemoveCard 2. EmvHandler add method: onSetRemoveCardResponse, onSetPromptResponse 3. Remove armeabi(include libnexgo\_emvjni.so, libnexgo\_gencode.so, libnexgo\_sdkemvjni.so). 4. Change SDK package format: jar-aar 5. CardReader add Felica method: setSupportFelica: if support Felica card |

## Página 3

SmartPos API Reference Manual: 09/08/21  
setFelicaRequestCode,  
setFelicaSystemCode  
6. CardReader add Read Mag Stripe original  
Track data method:  
setMagReaderRawData  
7. Delete method loadKeyByCom and  
cancelLoadKey  
8. Change others  
V2.1.1 2019-01-24 Hassan 1. Fixed mag-stripe reader issue  
2. Fixed emv onRequestAmount method  
3. Other fix  
V2.1.1 2019-03-18 ShaoPu Add Desfire card API  
V2.2.1 2019-06-20 Hassan 1. EMV add new funcion for Pure kernel  
2. add new API for set pure kernel Aid and  
CapkEntity  
3. add new API for set pure enable aid select  
first  
4. fix custom layout pinpad issue  
5. paypass add RRP function  
6. add new API for get sigature statue .  
7. add new API for security  
V2.2.1 2019-09-20 Randall 1. Add scanner 2 for customized UI scan  
V2.3.1 2019-12-20 Hassan 1. Add Mifare Ultralight API  
2. Add DRL for paywave and amex  
V2.3.3 2020-05-11 Hassan 1. Optimize MAC algorithm  
2. Fix paypass RRP issue  
3. Optimize emv flow  
4. other issue fixed  
V3.0.1 2020-06-11 Hassan 1. add emvHandler2 API  
2. Fix Pure card terminal capability issue.  
3. Remove the install and uninstall API  
V3.0.2 2020-10-21 Hassan 1. Emv add new MB kernel  
2. Compatible with N86  
3. Remove the HSM APIs  
4. other issue fixed  
V3.0.3 2021-04-12 Hassan 1. add arm64 library.  
2. Add PB contactless for Italy  
3. Add platform object, includes Install,  
Uninstall, Reboot, UpdateFirmware ..etc  
Confidential 3  
| | | | setFelicaRequestCode, setFelicaSystemCode 6. CardReader add Read Mag Stripe original Track data method: setMagReaderRawData 7. Delete method loadKeyByCom and cancelLoadKey 8. Change others |  
| --- | --- | --- | --- |  
| V2.1.1 | 2019-01-24 | Hassan | 1. Fixed mag-stripe reader issue 2. Fixed emv onRequestAmount method 3. Other fix |  
| V2.1.1 | 2019-03-18 | ShaoPu | Add Desfire card API |  
| V2.2.1 | 2019-06-20 | Hassan | 1. EMV add new funcion for Pure kernel 2. add new API for set pure kernel Aid and CapkEntity 3. add new API for set pure enable aid select first 4. fix custom layout pinpad issue 5. paypass add RRP function 6. add new API for get sigature statue . 7. add new API for security |  
| V2.2.1 | 2019-09-20 | Randall | 1. Add scanner 2 for customized UI scan |  
| V2.3.1 | 2019-12-20 | Hassan | 1. Add Mifare Ultralight API 2. Add DRL for paywave and amex |  
| V2.3.3 | 2020-05-11 | Hassan | 1. Optimize MAC algorithm 2. Fix paypass RRP issue 3. Optimize emv flow 4. other issue fixed |  
| V3.0.1 | 2020-06-11 | Hassan | 1. add emvHandler2 API 2. Fix Pure card terminal capability issue. 3. Remove the install and uninstall API |  
| V3.0.2 | 2020-10-21 | Hassan | 1. Emv add new MB kernel 2. Compatible with N86 3. Remove the HSM APIs 4. other issue fixed |  
| V3.0.3 | 2021-04-12 | Hassan | 1. add arm64 library. 2. Add PB contactless for Italy 3. Add platform object, includes Install, Uninstall, Reboot, UpdateFirmware ..etc |

## Página 4

SmartPos API Reference Manual: 09/08/21  
4. Emvhandler2 ,EMV contactless add read  
application data mode  
5. Emvhandler1 add onAfterFinalSelectedApp  
callback for MIR,RUPAY  
6. Add API dukptCipherKeyInject, inject the  
cipher BDK/IPEK key  
7. Other issue fixed  
V3.0.4 2021-08-09 Hassan 1. Add new API UsbSerial  
2. Fix the Paypass 9F7B tag process issue for  
emvHandler  
3. Optimize the process with card power on  
and power off  
4. Platform adds shutDownDevice ,  
enableUsbCdc, disableUsbCdc,  
getUsbCdcStatus, executeGeneralMethod  
5. Update the EMV L1 , L2 version code in  
emvKernelVersionInfo  
6. Fix get device model for N5 with  
getDeviceInfo method  
7. Add get firmWareFullVersion  
8. Integrate NexgoSystemSdk  
9. Other issue fixed  
Confidential 4  
| | | | 4. Emvhandler2 ,EMV contactless add read application data mode 5. Emvhandler1 add onAfterFinalSelectedApp callback for MIR,RUPAY 6. Add API dukptCipherKeyInject, inject the cipher BDK/IPEK key 7. Other issue fixed |  
| --- | --- | --- | --- |  
| V3.0.4 | 2021-08-09 | Hassan | 1. Add new API UsbSerial 2. Fix the Paypass 9F7B tag process issue for emvHandler 3. Optimize the process with card power on and power off 4. Platform adds shutDownDevice , enableUsbCdc, disableUsbCdc, getUsbCdcStatus, executeGeneralMethod 5. Update the EMV L1 , L2 version code in emvKernelVersionInfo 6. Fix get device model for N5 with getDeviceInfo method 7. Add get firmWareFullVersion 8. Integrate NexgoSystemSdk 9. Other issue fixed |  
| | | | |

## Página 5

SmartPos API Reference Manual: 09/08/21  
SOFTWARE LICENSE AGREEMENT  
IMPORTANT: YOU SHOULD CAREFULLY READ ALL THE TERMS, CONDITIONS AND RESTRICTIONS OF THIS  
LICENSE AGREEMENT BEFORE INSTALLING THE SOFTWARE PACKAGE. YOUR INSTALLATION OF THE  
SOFTWARE PACKAGE PRESUMES YOUR ACCEPTANCE OF THE TERMS, CONDITIONS, AND RESTRICTIONS  
CONTAINED IN THIS AGREEMENT. IF YOU DO NOT AGREE WITH THESE TERMS, CONDITIONS, AND  
RESTRICTIONS, PROMPTLY RETURN THE SOFTWARE PACKAGE AND ASSOCIATED DOCUMENTATION TO  
THE ADDRESS ON THE FRONT PAGE OF THIS DOCUMENT, ATTENTION: CUSTOMER SUPPORT.  
TERMS, CONDITIONS, AND RESTRICTIONS  
Shenzhen Xinguodu Technology Co., Ltd (the "Licensor") owns and has the right to distribute the  
described software and documentation, collectively referred to as the "Software”.  
LICENSE: Licensor grants you (the "Licensee") the right to use the Software in conjunction with  
Xinguodu products. LICENSEE MAY NOT COPY, MODIFY, OR TRANSFER THE SOFTWARE IN WHOLE OR IN  
PART EXCEPT AS EXPRESSLY PROVIDED IN THIS AGREEMENT. Licensee may not decompile, disassemble,  
or in any other manner attempt reverse reverse engineer the Software.  
Licensee shall not tamper with, bypass, or alter any security features of the software or attempt to do  
so.  
TRANSFER: Licensee may not transfer the Software or license to the Software to another party without  
the prior written authorization of the Licensor. If Licensee transfers the Software without authorization,  
all rights granted under this Agreement are automatically terminated.  
COPYRIGHT: The Software is copyrighted. Licensee may not copy the Software except for archival  
purposes or to load for business operations. All other copies of the Software are in violation of this  
Agreement.  
TERM: This Agreement is in effect as long as Licensee continues the use of the Software. The Licensor  
also reserves the right to terminate this Agreement if Licensee fails to comply with any of the terms,  
conditions, or restrictions contained herein. Should Licensor terminate this Agreement due to Licensee's  
failure to comply, Licensee agrees to return the Software to Licensor. Receipt of returned Software by  
the Licensor shall mark the termination.  
LIMITED WARRANTY: Licensor warrants to the Licensee that the disk(s) or other media on which the  
Software is recorded are free from defects in material or workmanship under normal use.  
THE SOFTWARE IS PROVIDED AS IS. LICENSOR MAKES NO OTHER WARRANTY OF ANY KIND, EITHER  
EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF  
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.  
Because of the diversity of conditions and PC hardware under which the Software may be used, Licensor  
does not warrant that the software will meet Licensee specifications or that the operation of the  
Software will be uninterrupted or free of errors.  
IN NO EVENT WILL LICENSOR BE LIABLE FOR ANY DAMAGES, INCLUDING ANY LOST PROFITS, LOST  
SAVINGS, OR OTHER INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE, OR  
INABILITY TO USE, THE SOFTWARE. Licensee's sole remedy in the event of a defect in material or  
workmanship is expressly limited to replacement of the Software disk(s), if applicable.  
GOVERNING LAW: If any provision of this Agreement is found to be unlawful, void, or unenforceable,  
that provision shall be removed from consideration under this Agreement and will not affect the  
Confidential 5

## Página 6

SmartPos API Reference Manual: 09/08/21  
enforceability of any of the remaining provisions. This Agreement shall be governed by the Laws of the  
State of California and shall inure to the benefit of Shenzhen Xinguodu Technology Co., Ltd, its  
successors or assigns.  
ACKNOWLEDGMENT: LICENSEE ACKNOWLEDGES THAT HE HAS READ THIS AGREEMENT, UNDERSTANDS  
ALL OF ITS TERMS, CONDITIONS, AND RESTRICTIONS, AND AGREES TO BE BOUND BY THEM. LICENSEE  
ALSO AGREES THAT THIS AGREEMENT SUPERSEDES ANY AND ALL VERBAL AND WRITTEN  
COMMUNICATIONS BETWEEN LICENSOR AND LICENSEE OR THEIR ASSIGNS RELATING TO THE SUBJECT  
MATTER OF THIS AGREEMENT.  
QUESTIONS REGARDING THIS AGREEMENT SHOULD BE ADDRESSED IN WRITING TO Shenzhen Xinguodu  
Technology Co., Ltd, ATTENTION: CUSTOMER SUPPORT, AT THE ADDRESS LISTED IN THIS DOCUMENT OR  
E-MAILED TO SUPPORT@XINGUODU.COM.  
Confidential 6

## Página 7

SmartPos API Reference Manual: 09/08/21  
Table of Content  
1 Introduction ........................................................................................................................................ 21  
1.1 Demo Description ....................................................................................................................... 21  
1.2 The Term ..................................................................................................................................... 21  
1.3 SDK Content ................................................................................................................................ 21  
1.4 System Requirements ................................................................................................................. 21  
2 How to Create a Project ...................................................................................................................... 21  
3 Class methods ..................................................................................................................................... 23  
3.1 LED class ...................................................................................................................................... 23  
3.1.1 SetLed ...................................................................................................................................... 24  
3.2 Printer class ................................................................................................................................. 24  
3.2.1 initPrinter ................................................................................................................................ 26  
3.2.2 getStatus ................................................................................................................................. 27  
3.2.3 appendImage .......................................................................................................................... 27  
3.2.4 appendPrnStr .......................................................................................................................... 28  
3.2.5 appendPrnStr .......................................................................................................................... 28  
3.2.6 appendPrnStr .......................................................................................................................... 29  
3.2.7 appendPrnStr .......................................................................................................................... 30  
3.2.8 appendBarcode ....................................................................................................................... 31  
3.2.9 appendQRCode ....................................................................................................................... 32  
3.2.10 appendQRcode.................................................................................................................... 32  
3.2.11 feedPaper ............................................................................................................................ 33  
3.2.12 cutPaper .............................................................................................................................. 33  
3.2.13 startPrint ............................................................................................................................. 34  
3.2.14 setLetterSpacing.................................................................................................................. 34  
Confidential 7

## Página 8

SmartPos API Reference Manual: 09/08/21  
3.2.15 setGray ................................................................................................................................ 34  
3.2.16 setTypeface ......................................................................................................................... 35  
3.3 Pinpad Class ................................................................................................................................ 35  
3.3.1 initPinPad ................................................................................................................................ 36  
3.3.2 setAlgorithmMode .................................................................................................................. 37  
3.3.3 setCipherMode ....................................................................................................................... 37  
3.3.4 setCipherInitializationVector .................................................................................................. 37  
3.3.5 setPinKeyboardMode.............................................................................................................. 37  
3.3.6 writeMKey ............................................................................................................................... 38  
3.3.7 writeMKey ............................................................................................................................... 38  
3.3.8 isKeyExist ................................................................................................................................. 39  
3.3.9 calcWKeyKCV .......................................................................................................................... 39  
3.3.10 writeWKey ........................................................................................................................... 40  
3.3.11 isKeyExist ............................................................................................................................. 41  
3.3.12 calcByWkey ......................................................................................................................... 41  
3.3.13 desByWKey.......................................................................................................................... 42  
3.3.14 encryptTrackData ................................................................................................................ 43  
3.3.15 calcMac ............................................................................................................................... 43  
3.3.16 calcMac ............................................................................................................................... 44  
3.3.17 calcMac(DUKPT) .................................................................................................................. 45  
3.3.18 encryptByMKey ................................................................................................................... 45  
3.3.19 setPinpadLayout .................................................................................................................. 46  
3.3.20 inputOnlinePin .................................................................................................................... 47  
3.3.21 inputOfflinePin .................................................................................................................... 47  
3.3.22 isInputting ........................................................................................................................... 48  
Confidential 8

## Página 9

SmartPos API Reference Manual: 09/08/21  
3.3.23 cancelInput .......................................................................................................................... 48  
3.3.24 format ................................................................................................................................. 48  
3.3.25 deleteMKey ......................................................................................................................... 49  
3.3.26 dukptKeyInject .................................................................................................................... 49  
3.3.27 dukptCipherKeyInject .......................................................................................................... 50  
3.3.28 dukptKsnIncrease ................................................................................................................ 51  
3.3.29 dukptCurrentKsn ................................................................................................................. 51  
3.3.30 dukptEncrypt ....................................................................................................................... 52  
3.3.31 dukptEncrypt ....................................................................................................................... 52  
3.4 Scaner#1(default UI) ................................................................................................................... 53  
3.4.1 initScanner .............................................................................................................................. 54  
3.4.2 startScan ................................................................................................................................. 56  
3.4.3 stopScan .................................................................................................................................. 56  
3.4.4 decode ..................................................................................................................................... 56  
3.5 Scanner#2(customizable UI) ...................................................................................................... 57  
3.5.1 initScanner .............................................................................................................................. 57  
3.5.2 getBestPreviewSize ................................................................................................................. 59  
3.5.3 setSurface................................................................................................................................ 59  
3.5.4 start ......................................................................................................................................... 60  
3.5.5 stop ......................................................................................................................................... 60  
3.5.6 switchCamera .......................................................................................................................... 60  
3.5.7 flashTrigger ............................................................................................................................. 60  
3.5.8 focusTrigger ............................................................................................................................ 61  
3.5.9 setZoom .................................................................................................................................. 61  
3.6 Card Reader Class ........................................................................................................................ 61  
Confidential 9

## Página 10

SmartPos API Reference Manual: 09/08/21  
3.6.1 searchCard .............................................................................................................................. 63  
3.6.2 stopSearch............................................................................................................................... 64  
3.6.3 isCardExist ............................................................................................................................... 64  
3.6.4 open ........................................................................................................................................ 65  
3.6.5 close ........................................................................................................................................ 66  
3.6.6 getRfCardType ......................................................................................................................... 66  
3.6.7 setETU ..................................................................................................................................... 67  
3.6.8 setSupportFelica ..................................................................................................................... 68  
3.6.9 setFelicaSystemCode .............................................................................................................. 68  
3.6.10 setFelicaRequestCode ......................................................................................................... 69  
3.7 CPU Cards .................................................................................................................................... 69  
3.7.1 readUid .................................................................................................................................... 69  
3.7.2 powerOn ................................................................................................................................. 70  
3.7.3 active ....................................................................................................................................... 70  
3.7.4 exchangeAPDUCmd ................................................................................................................ 70  
3.7.5 exchangeAPDUCmd ................................................................................................................ 71  
3.7.6 powerOff ................................................................................................................................. 72  
3.7.7 remove .................................................................................................................................... 72  
3.8 EMV class (Emvhandler2) ............................................................................................................ 72  
3.8.1 delAllAid .................................................................................................................................. 72  
3.8.2 delOneAid ............................................................................................................................... 73  
3.8.3 delAllCapk ............................................................................................................................... 73  
3.8.4 delOneCapk ............................................................................................................................. 73  
3.8.5 setAidParaList .......................................................................................................................... 74  
3.8.6 setAidParaList .......................................................................................................................... 75  
Confidential 10

## Página 11

SmartPos API Reference Manual: 09/08/21  
3.8.7 setAidParaList .......................................................................................................................... 76  
3.8.8 setCAPKList .............................................................................................................................. 76  
3.8.9 setCAPKList .............................................................................................................................. 77  
3.8.10 setCAPKList .......................................................................................................................... 78  
3.8.11 getAidListNum ..................................................................................................................... 78  
3.8.12 getAidList............................................................................................................................. 79  
3.8.13 getCapkListNum .................................................................................................................. 79  
3.8.14 getCapkList .......................................................................................................................... 80  
3.8.15 emvDebugLog ..................................................................................................................... 80  
3.8.16 setDynamicReaderLimitListForPaywave ............................................................................. 81  
3.8.17 setDynamicReaderLimitListForExpressPay ......................................................................... 81  
3.8.18 getTlv ................................................................................................................................... 82  
3.8.19 getTlvByTags ....................................................................................................................... 83  
3.8.20 setTlv ................................................................................................................................... 83  
3.8.21 initTermConfig .................................................................................................................... 84  
3.8.22 emvProcess ......................................................................................................................... 84  
3.8.23 onSetSelAppResponse ........................................................................................................ 87  
3.8.24 onSetTransInitBeforeGPOResponse ................................................................................... 87  
3.8.25 onSetConfirmCardNoResponse .......................................................................................... 87  
3.8.26 onSetPinInputResponse ...................................................................................................... 88  
3.8.27 onSetContactlessTapCardResponse .................................................................................... 88  
3.8.28 onSetOnlineProcResponse .................................................................................................. 88  
3.8.29 onSetPromptResponse ....................................................................................................... 89  
3.8.30 onSetRemoveCardResponse ............................................................................................... 89  
3.8.31 EMVProcessCancel .............................................................................................................. 89  
Confidential 11

## Página 12

SmartPos API Reference Manual: 09/08/21  
3.8.32 EMVProcessAbort ............................................................................................................... 90  
3.8.33 getEmvContactlessMode .................................................................................................... 90  
3.8.34 contactlessSetAidFirstSelect ............................................................................................... 90  
3.8.35 setPureKernelCapab........................................................................................................ 91  
3.8.36 setJcbContactlessTIP ...................................................................................................... 91  
3.8.37 setRupayTransType ......................................................................................................... 92  
3.8.38 getJcbContactlessTIP ...................................................................................................... 93  
3.8.39 getSignNeed ........................................................................................................................ 93  
3.8.40 getEmvCvmResult............................................................................................................ 93  
3.8.41 getEmvCardDataInfo ....................................................................................................... 94  
3.8.42 getEmvContactlessKernelId ........................................................................................... 95  
3.8.43 contactlessAppendAidIntoKernel ................................................................................... 95  
3.8.44 getPayWaveResult ........................................................................................................... 96  
3.9 EMV class(Emvhandler) Deprecated ........................................................................................... 97  
3.9.1 delAllAid .................................................................................................................................. 99  
3.9.2 delOneAid ............................................................................................................................... 99  
3.9.3 delAllCapk ............................................................................................................................... 99  
3.9.4 delOneCapk ............................................................................................................................. 99  
3.9.5 setAidParaList ........................................................................................................................ 100  
3.9.6 setAidParaList ........................................................................................................................ 101  
3.9.7 setAidParaList ........................................................................................................................ 101  
3.9.8 setCAPKList ............................................................................................................................ 102  
3.9.9 setCAPKList ............................................................................................................................ 103  
Confidential 12

## Página 13

SmartPos API Reference Manual: 09/08/21  
3.9.10 setCAPKList ........................................................................................................................ 103  
3.9.11 setDynamicReaderLimitList ............................................................................................... 104  
3.9.12 setDynamicReaderLimitListForExpressPay ....................................................................... 105  
3.9.13 initTermConfig .................................................................................................................. 106  
3.9.14 emvProcess ....................................................................................................................... 106  
3.9.15 onSetSelAppResponse ...................................................................................................... 108  
3.9.16 onSetAfterFinalSelectedAppResponse ............................................................................. 108  
3.9.17 onSetRequestAmountResponse ....................................................................................... 109  
3.9.18 onSetConfirmEcSwitchResponse ...................................................................................... 109  
3.9.19 onSetConfirmCardNoResponse ........................................................................................ 109  
3.9.20 onSetPinInputResponse .................................................................................................... 109  
3.9.21 onsetCertVerifyResponse.................................................................................................. 110  
3.9.22 onSetReadCardAgainResponse ......................................................................................... 110  
3.9.23 onSetOnlineProcResponse ................................................................................................ 110  
3.9.24 onSetPromptResponse ..................................................................................................... 111  
3.9.25 onSetRemoveCardResponse ............................................................................................. 111  
3.9.26 getTlv ................................................................................................................................. 112  
3.9.27 getTlvByTags ..................................................................................................................... 112  
3.9.28 setTlv ................................................................................................................................. 113  
3.9.29 getEMVCardLog................................................................................................................. 113  
3.9.30 Clear the Log ..................................................................................................................... 114  
3.9.31 EMVGetEcBalance ............................................................................................................. 114  
3.9.32 EMVProcessCancel ............................................................................................................ 114  
3.9.33 emvDebugLog ................................................................................................................... 115  
3.9.34 getEmvContactlessMode .................................................................................................. 115  
Confidential 13

## Página 14

SmartPos API Reference Manual: 09/08/21  
3.9.35 getAidListNum ................................................................................................................... 115  
3.9.36 getAidList........................................................................................................................... 116  
3.9.37 getCapkListNum ................................................................................................................ 116  
3.9.38 getCapkList ........................................................................................................................ 117  
3.9.39 newDelAllAid ..................................................................................................................... 117  
3.9.40 newDelOneAid .................................................................................................................. 117  
3.9.41 newDelAllCapk .................................................................................................................. 118  
3.9.42 newDelOneCapk ................................................................................................................ 118  
3.9.43 newSetAidParaList ............................................................................................................ 119  
3.9.44 newSetAidParaList ............................................................................................................ 119  
3.9.45 newSetAidParaList ............................................................................................................ 120  
3.9.46 newSetCAPKList ................................................................................................................ 121  
3.9.47 newSetCAPKList ................................................................................................................ 121  
3.9.48 newSetCAPKList ................................................................................................................ 122  
3.9.49 newGetAidListNum ........................................................................................................... 123  
3.9.50 newGetAidList ................................................................................................................... 123  
3.9.51 newGetCapkListNum ........................................................................................................ 124  
3.9.52 newGetCapkList ................................................................................................................ 124  
3.9.53 selectAidFirst ..................................................................................................................... 125  
3.9.54 getSignNeed ...................................................................................................................... 125  
3.9.55 setPureKernelCapab .......................................................................................................... 126  
3.10 setSystemClock ......................................................................................................................... 126  
3.11 getDeviceInfo ............................................................................................................................ 127  
3.12 Serial class ................................................................................................................................. 128  
3.12.1 disconnect ......................................................................................................................... 130  
Confidential 14

## Página 15

SmartPos API Reference Manual: 09/08/21  
3.12.2 connect.............................................................................................................................. 130  
3.12.3 clrBuffer ............................................................................................................................ 130  
3.12.4 send ................................................................................................................................... 131  
3.12.5 recv .................................................................................................................................... 131  
3.13 Buzzer class ............................................................................................................................... 132  
3.13.1 beep .................................................................................................................................. 132  
3.14 M1 Cards ................................................................................................................................... 133  
3.14.1 authority ............................................................................................................................ 133  
3.14.2 readBlock .......................................................................................................................... 134  
3.14.3 readBlockValue ................................................................................................................. 135  
3.14.4 writeBlock ......................................................................................................................... 136  
3.14.5 writeBlockValue ................................................................................................................ 136  
3.14.6 operateBlock ..................................................................................................................... 137  
3.15 MemoryCard ............................................................................................................................. 138  
3.15.1 reset .................................................................................................................................. 139  
3.15.2 read ................................................................................................................................... 140  
3.15.3 write .................................................................................................................................. 141  
3.15.4 erase .................................................................................................................................. 142  
3.15.5 verify ................................................................................................................................. 143  
3.15.6 readEC ............................................................................................................................... 145  
3.15.7 updateEC ........................................................................................................................... 146  
3.15.8 powerOff ........................................................................................................................... 147  
3.16 Desfire Cards ............................................................................................................................. 147  
3.16.1 Authenticate ..................................................................................................................... 150  
3.16.2 AuthenticateIso ................................................................................................................. 150  
Confidential 15

## Página 16

SmartPos API Reference Manual: 09/08/21  
3.16.3 AuthenticateAes ................................................................................................................ 151  
3.16.4 changeKeySettings ............................................................................................................ 151  
3.16.5 getKeySettings................................................................................................................... 153  
3.16.6 changePiccMasterkey ....................................................................................................... 153  
3.16.7 changeAppKey................................................................................................................... 154  
3.16.8 getKeyVersion ................................................................................................................... 155  
3.16.9 createApplication .............................................................................................................. 155  
3.16.10 deleteApplication .............................................................................................................. 156  
3.16.11 getAids .............................................................................................................................. 157  
3.16.12 getDfNames ...................................................................................................................... 157  
3.16.13 selectApplication ............................................................................................................... 158  
3.16.14 formatPicc ......................................................................................................................... 158  
3.16.15 getVersion ......................................................................................................................... 159  
3.16.16 getFreeMemory ................................................................................................................ 160  
3.16.17 setConfiguration ............................................................................................................... 161  
3.16.18 getCardUid ........................................................................................................................ 162  
3.16.19 getFids ............................................................................................................................... 162  
3.16.20 getIsoFids .......................................................................................................................... 162  
3.16.21 getFileSettings ................................................................................................................... 163  
3.16.22 changeFileSettings ............................................................................................................ 164  
3.16.23 createStdDataFile .............................................................................................................. 165  
3.16.24 createBackupDatafile ........................................................................................................ 167  
3.16.25 createValueFile ................................................................................................................. 167  
3.16.26 createLinearRecordFile ..................................................................................................... 169  
3.16.27 createCyclicRecordFile ...................................................................................................... 171  
Confidential 16

## Página 17

SmartPos API Reference Manual: 09/08/21  
3.16.28 deleteFile ........................................................................................................................... 171  
3.16.29 readData............................................................................................................................ 172  
3.16.30 writeData .......................................................................................................................... 173  
3.16.31 getValue ............................................................................................................................ 174  
3.16.32 credit ................................................................................................................................. 174  
3.16.33 debit .................................................................................................................................. 175  
3.16.34 limitedCredit ..................................................................................................................... 176  
3.16.35 writeRecord ....................................................................................................................... 177  
3.16.36 readRecords ...................................................................................................................... 178  
3.16.37 clearRecordFile .................................................................................................................. 179  
3.16.38 commitTransaction ........................................................................................................... 180  
3.16.39 abortTransaction ............................................................................................................... 180  
3.17 Mifare Ultralight card................................................................................................................ 181  
3.17.1 authority ............................................................................................................................ 181  
3.17.2 readBlock .......................................................................................................................... 182  
3.17.3 writeBlock ......................................................................................................................... 182  
3.17.4 exchangeCmd .................................................................................................................... 182  
3.18 Platform .................................................................................................................................... 183  
3.18.1 installApp .......................................................................................................................... 183  
3.18.2 unInstallApp ...................................................................................................................... 183  
3.18.3 updateFirmware ................................................................................................................ 184  
3.18.4 reboot................................................................................................................................ 184  
3.18.5 shutDownDevice ............................................................................................................... 185  
3.18.6 enableHomeButton ........................................................................................................... 185  
3.18.7 disableHomeButton .......................................................................................................... 185  
Confidential 17

## Página 18

SmartPos API Reference Manual: 09/08/21  
3.18.8 enableTaskButton ............................................................................................................. 185  
3.18.9 disableTaskButton ............................................................................................................. 186  
3.18.10 enableControlBar .............................................................................................................. 186  
3.18.11 disableControlBar.............................................................................................................. 186  
3.18.12 enablePowerButton .......................................................................................................... 187  
3.18.13 disablePowerButton.......................................................................................................... 187  
3.18.14 setBeepMode .................................................................................................................... 187  
3.18.15 enableUsbCdc ................................................................................................................... 188  
3.18.16 disableUsbCdc ................................................................................................................... 188  
3.18.17 getUsbCdcStatus ............................................................................................................... 188  
3.18.18 executeGeneralMethod .................................................................................................... 189  
3.19 Usb Serial class .......................................................................................................................... 189  
3.19.1 open .................................................................................................................................. 189  
3.19.2 close ................................................................................................................................. 190  
3.19.3 clrBuffer ............................................................................................................................ 191  
3.19.4 write .................................................................................................................................. 191  
3.19.5 read ................................................................................................................................... 191  
4 Callback information ......................................................................................................................... 192  
4.1 OnPrintListener ......................................................................................................................... 192  
4.1.1 onPrintResult ........................................................................................................................ 192  
4.2 OnPinPadInputListener ............................................................................................................. 192  
4.2.1 onInputResult ........................................................................................................................ 192  
4.2.2 onSendKey ............................................................................................................................ 193  
4.3 OnScanner Listener ................................................................................................................... 194  
4.3.1 onInitResult ........................................................................................................................... 194  
Confidential 18

## Página 19

SmartPos API Reference Manual: 09/08/21  
4.3.2 onScannerResult ................................................................................................................... 194  
4.4 OnCardInfoListener ................................................................................................................... 194  
4.4.1 onCardInfo ............................................................................................................................ 194  
4.4.2 onSwipeIncorrect .................................................................................................................. 196  
4.4.3 onMultipleCards .................................................................................................................... 196  
4.5 OnEMVProcessListener2 ........................................................................................................... 196  
4.5.1 onSelApp ............................................................................................................................... 197  
4.5.2 onTransInitBeforeGPO .......................................................................................................... 197  
4.5.3 onConfirmCardNo ................................................................................................................. 197  
4.5.4 onCardHolderInputPin .......................................................................................................... 198  
4.5.5 onContactlessTapCardAgain ................................................................................................. 198  
4.5.6 onOnlineProc ........................................................................................................................ 198  
4.5.7 onPrompt .............................................................................................................................. 199  
4.5.8 onRemoveCard ...................................................................................................................... 199  
4.5.9 onFinish ................................................................................................................................. 200  
4.6 OnEMVProcessListener Deprecated ........................................................................................ 201  
4.6.1 onSelApp ............................................................................................................................... 201  
4.6.2 onAfterFinalSelectedApp ...................................................................................................... 202  
4.6.3 onRequestAmount ................................................................................................................ 202  
4.6.4 onConfirmEcSwitch ............................................................................................................... 202  
4.6.5 onConfirmCardNo ................................................................................................................. 202  
4.6.6 onCardHolderInputPin .......................................................................................................... 203  
4.6.7 onCertVerify .......................................................................................................................... 203  
4.6.8 onReadCardAgain .................................................................................................................. 203  
4.6.9 onOnlineProc ........................................................................................................................ 204  
Confidential 19

## Página 20

SmartPos API Reference Manual: 09/08/21  
4.6.10 onPrompt .......................................................................................................................... 204  
4.6.11 onRemoveCard .................................................................................................................. 204  
4.6.12 onFinish ............................................................................................................................. 205  
4.7 OnAppOperatListener ............................................................................................................... 206  
4.7.1 onOperatResult ..................................................................................................................... 206  
4.8 OnUsbSerialReadListener.......................................................................................................... 206  
4.8.1 onReadResult ........................................................................................................................ 207  
Appendix ................................................................................................................................................... 207  
Confidential 20

## Página 21

SmartPos API Reference Manual: 09/08/21  
1 Introduction  
This document helps developers develop third-party applications on N5 devices. The company provides  
API interface in the form of jar package, based on the API interface, the developer can conveniently and  
efficiently develop third-party applications to meet the personalized needs of developers.  
1.1 Demo Description  
The demo program demonstrates how to use the aar package to call API's various interfaces to meet the  
needs of the developer.  
1.2 The Term  
SdkResult returns the value class. The fields in the class define all the Return Values in the document. All  
fields are described in the Appendix.  
1.3 SDK Content  
File Description  
nexgo-smart-sdk-vx.x.x.aar aar package API interface  
1.4 System Requirements  
Development environment: Android Studio 2.0 or later  
N5 Operating System Version: 5.1.1  
2 How to Create a Project  
To add the jar package to the developer project, follow these steps:  
1) With Android studio to create or open the customer's project.  
Confidential 21  
| File | Description |  
| --- | --- |  
| nexgo-smart-sdk-vx.x.x.aar | aar package API interface |

## Página 22

SmartPos API Reference Manual: 09/08/21  
2) Copy nexgo-smart-sdk-vx.x.x.aar to libs / directory, configure project build.gradle to load aar  
package.  
repositories{  
flatDir{  
dirs 'libs'  
}  
}  
dependencies{  
complie(name: 'nexgo-smartpos-sdk-vx.x.x', ext: 'aar')  
}  
Confidential 22

## Página 23

SmartPos API Reference Manual: 09/08/21  
3) Get the global object of the device operation.  
DeviceEngine deviceEngine = APIProxy getDeviceEngine (this) ;  
4) Get the object of the device sub-module, and operate the interface  
For example: get buzzer operation object, ring 500 milliseconds.  
final Beeper beeper = deviceEngine getBeeper () .;  
beeper.beep (500);  
3 Class methods  
The following is divided into 10 categories, 5 global methods. First, get the object of each class, then call  
the member method in the class.  
3.1 LED class  
LED class is responsible for managing POS LED lights.  
Get the object of the LED class:  
LEDDriver ledDriver = deviceEngine.getLEDDriver ();  
This module operates using the basic flow chart:  
Confidential 23

## Página 24

SmartPos API Reference Manual: 09/08/21  
Start  
Obtain context  
Obtain LED object  
Call settled method  
3.1.1 SetLed  
Drive POS red, green, yellow, blue light switch.  
Public void setLed (LightModeEnum light, boolean isOn);  
Parameters:  
Parameter Description  
light Enumerated type red, green, yellow, blue LED lights  
isOn True: on, false: off  
LightModeEnum  
Enumeration Name Description  
RED Red light  
GREEN Green light  
YELLOW Yellow light  
BLUE Blue light  
Return Value: None  
3.2 Printer class  
The printer class is responsible for managing POS printers.  
Confidential 24  
| Parameter | Description |  
| --- | --- |  
| light | Enumerated type red, green, yellow, blue LED lights |  
| isOn | True: on, false: off |  
| Enumeration Name | Description |  
| --- | --- |  
| RED | Red light |  
| GREEN | Green light |  
| YELLOW | Yellow light |  
| BLUE | Blue light |

## Página 25

SmartPos API Reference Manual: 09/08/21  
Get the object of the printer class:  
Printer printer = deviceEngine getPrinter (). ;  
The following table shows the Return Values supported by the method of the printer class:  
Constant Name Constant Value Description  
Printer\_Base\_Error -1000  
Printer\_Print\_Fail Printer\_Base\_Error – 1 Print failed  
Printer\_AddPrnStr\_Fail Printer\_Base\_Error – 2 Setting string buffer failed  
Printer\_AddImg\_Fail Printer\_Base\_Error – 3 Setting image buffer failed  
Printer\_Busy Printer\_Base\_Error – 4 The printer is busy  
Printer\_PaperLack Printer\_Base\_Error – 5 The printer is out of paper  
Printer\_Wrong\_Package Printer\_Base\_Error – 6 Print packet is wrong  
Printer\_Fault Printer\_Base\_Error – 7 Printer failure  
Printer\_TooHot Printer\_Base\_Error – 8 The printer is overheating  
Printer\_UnFinished Printer\_Base\_Error – 9 The print is not complete  
Printer\_NoFontLib Printer\_Base\_Error – 10 The printer does not have a font  
Printer\_OutOfMemory Printer\_Base\_Error – 11 The packet is too long  
Printer\_Other\_Error Printer\_Base\_Error-999 Other exception error  
This module operates using the basic flow chart:  
Confidential 25  
| Constant Name | Constant Value | Description |  
| --- | --- | --- |  
| Printer\_Base\_Error | -1000 | |  
| Printer\_Print\_Fail | Printer\_Base\_Error – 1 | Print failed |  
| Printer\_AddPrnStr\_Fail | Printer\_Base\_Error – 2 | Setting string buffer failed |  
| Printer\_AddImg\_Fail | Printer\_Base\_Error – 3 | Setting image buffer failed |  
| Printer\_Busy | Printer\_Base\_Error – 4 | The printer is busy |  
| Printer\_PaperLack | Printer\_Base\_Error – 5 | The printer is out of paper |  
| Printer\_Wrong\_Package | Printer\_Base\_Error – 6 | Print packet is wrong |  
| Printer\_Fault | Printer\_Base\_Error – 7 | Printer failure |  
| Printer\_TooHot | Printer\_Base\_Error – 8 | The printer is overheating |  
| Printer\_UnFinished | Printer\_Base\_Error – 9 | The print is not complete |  
| Printer\_NoFontLib | Printer\_Base\_Error – 10 | The printer does not have a font |  
| Printer\_OutOfMemory | Printer\_Base\_Error – 11 | The packet is too long |  
| Printer\_Other\_Error | Printer\_Base\_Error-999 | Other exception error |

## Página 26

SmartPos API Reference Manual: 09/08/21  
Start  
Get context  
Get printer object  
Call initprinter to initialize  
printer  
Call getstatus to check if  
paper is loaded  
If not, call setletterspacing to set  
gray typeface, spacing, and font;  
or use default  
Use appendimage to append  
image, use appendprntstr to  
append string, use  
appendQRcode to append  
QRcode  
Call feedpaper to feed paper or  
call cutpaper to cut the paper  
Call startprint to start printing  
3.2.1 initPrinter  
Confidential 26

## Página 27

SmartPos API Reference Manual: 09/08/21  
Initialize the printer.  
public int initPrinter ();  
Parameters: None  
Return Value:  
SdkResult.Success success  
3.2.2 getStatus  
Get the printer status.  
Public int getStatus ();  
Parameters: None  
Return Value:  
SdkResult.Success print successful  
SdkResult. Printer\_UnFinished print is not complete  
SdkResult. Printer\_PaperLack printer is out of paper  
SdkResult. Printer\_Too\_Hot printer is overheating  
SdkResult. Printer\_Fail print failed  
SdkResult.Fail other errors  
3.2.3 appendImage  
Append bitmap.  
Public int appendImage (Bitmap bitmap, AlignEnum align);  
Parameters:  
Parameter Description  
bitmap Bitmap data  
align Enumerated type of alignment  
AlignEnum  
Enumeration Name Description  
LEFT Left alignment  
Confidential 27  
| Parameter | Description |  
| --- | --- |  
| bitmap | Bitmap data |  
| align | Enumerated type of alignment |  
| Enumeration Name | Description |  
| --- | --- |  
| LEFT | Left alignment |

## Página 28

SmartPos API Reference Manual: 09/08/21  
RIGHT Right alignment  
CENTER Centered  
Return Value:  
SdkResult.Success success  
SdkResult.Printer\_AddImg\_Fail additional picture failure  
3.2.4 appendPrnStr  
Add text.  
Public int appendPrnStr (String text, int fontsize, AlignEnum align,  
Boolean isBoldFont);  
Parameters:  
Parameter Description  
text The string data to be added  
fontSize Font Size small: 16; normal: 20; large: 24; x-large: 32  
align Enumerated type of alignment  
isBoldFont Whether bold, true: yes, false: no  
AlignEnum  
Enumeration Name Description  
LEFT Left alignment  
RIGHT Right alignment  
CENTER Centered  
Return Value:  
SdkResult.Success success  
SdkResult.Printer\_Wrong\_Package print packet format error  
SdkResult.Printer\_AddPrnStr\_Fail string buffer is set to fail  
3.2.5 appendPrnStr  
Add text.  
public int appendPrnStr(String text,int fontsize,AlignEnum align,  
Confidential 28  
| RIGHT | Right alignment |  
| --- | --- |  
| CENTER | Centered |  
| Parameter | Description |  
| --- | --- |  
| text | The string data to be added |  
| fontSize | Font Size small: 16; normal: 20; large: 24; x-large: 32 |  
| align | Enumerated type of alignment |  
| isBoldFont | Whether bold, true: yes, false: no |  
| Enumeration Name | Description |  
| --- | --- |  
| LEFT | Left alignment |  
| RIGHT | Right alignment |  
| CENTER | Centered |

## Página 29

SmartPos API Reference Manual: 09/08/21  
boolean isBoldFont, LineOptionEntity ops);  
Parameters:  
Parameter Description  
text The string data to be added  
fontSize Font Size small: 16; normal: 20; large: 24; x-large:  
32  
align Enumerated type of alignment  
isBoldFont Whether bold, true: yes, false: no  
ops LineOptionEntity :additional option  
AlignEnum  
Enumeration Name Description  
LEFT Left alignment  
RIGHT Right alignment  
CENTER Centered  
LineOptionEntity  
attribute Description  
boolean isUnderline Print underline: true: yes; false:no  
int marginLeft Left margin size  
Return Value:  
SdkResult.Success success  
SdkResult.Printer\_Wrong\_Package print packet format error  
SdkResult.Printer\_AddPrnStr\_Fail string buffer is set to fail  
3.2.6 appendPrnStr  
Append string both sides at the same time.  
Public int appendPrnStr (String leftText, String rightText, int fontsize,  
AlignEnum align, Boolean isBoldFont);  
Parameters:  
Parameter Description  
leftText Left alignment data  
rightText Right alignment data  
fontsize Font Size small: 16; normal: 20; large: 24; x-large: 32  
Confidential 29  
| Parameter | Description |  
| --- | --- |  
| text | The string data to be added |  
| fontSize | Font Size small: 16; normal: 20; large: 24; x-large: 32 |  
| align | Enumerated type of alignment |  
| isBoldFont | Whether bold, true: yes, false: no |  
| ops | LineOptionEntity :additional option |  
| Enumeration Name | Description |  
| --- | --- |  
| LEFT | Left alignment |  
| RIGHT | Right alignment |  
| CENTER | Centered |  
| attribute | Description |  
| --- | --- |  
| boolean isUnderline | Print underline: true: yes; false:no |  
| int marginLeft | Left margin size |  
| Parameter | Description |  
| --- | --- |  
| leftText | Left alignment data |  
| rightText | Right alignment data |  
| fontsize | Font Size small: 16; normal: 20; large: 24; x-large: 32 |

## Página 30

SmartPos API Reference Manual: 09/08/21  
align Enumerated type of alignment  
isBoldFont Whether bold, true: yes, false: no  
AlignEnum  
Enumeration Name Description  
LEFT Left alignment  
RIGHT Right alignment  
CENTER Centered  
Return Value:  
SdkResult.Success success  
SdkResult.Printer\_Wrong\_Package print packet format error  
SdkResult.Printer\_AddPrnStr\_Fail string buffer is set to fail  
3.2.7 appendPrnStr  
Append string both sides at the same time.  
public int appendPrnStr(String leftText,String rightText,int fontsize,  
Boolean isBoldFont, LineOptionEntity ops);  
Parameters：  
Parameter Description  
leftText Left alignment data  
rightText Right alignment data  
fontsize Font Size small: 16; normal: 20; large: 24; x-large:  
32  
align Enumerated type of alignment  
ops LineOptionEntity :additional option  
LineOptionEntity  
attribute Description  
boolean isUnderline Print underline: true: yes; false:no  
int marginLeft Left margin size  
Return Value:  
SdkResult.Success success  
Confidential 30  
| align | Enumerated type of alignment |  
| --- | --- |  
| isBoldFont | Whether bold, true: yes, false: no |  
| Enumeration Name | Description |  
| --- | --- |  
| LEFT | Left alignment |  
| RIGHT | Right alignment |  
| CENTER | Centered |  
| Parameter | Description |  
| --- | --- |  
| leftText | Left alignment data |  
| rightText | Right alignment data |  
| fontsize | Font Size small: 16; normal: 20; large: 24; x-large: 32 |  
| align | Enumerated type of alignment |  
| ops | LineOptionEntity :additional option |  
| attribute | Description |  
| --- | --- |  
| boolean isUnderline | Print underline: true: yes; false:no |  
| int marginLeft | Left margin size |

## Página 31

SmartPos API Reference Manual: 09/08/21  
SdkResult.Printer\_Wrong\_Package print packet format error  
SdkResult.Printer\_AddPrnStr\_Fail string buffer is set to fail  
3.2.8 appendBarcode  
Append barcode.  
public int appendBarcode (String content, BarcodeFormatEnum format, int width, int height,  
AlignEnum align);  
Parameters:  
Parameter Description  
content Generates Barcode data  
format Barcode format  
width Generate Barcode width for printing; range 1-384  
height Generates Barcode height for printing; ranges greater than 0  
align Alignment  
BarcodeFormatEnum  
Format Name Description  
AZTEC  
CODABAR  
CODE\_39  
CODE\_93  
CODE\_128  
DATA\_MATRIX  
EAN\_8  
EAN\_13  
ITF  
MAXICODE  
PDF\_417  
QR\_CODE  
RSS\_14  
RSS\_EXPANDED  
UPC\_A  
Confidential 31  
| Parameter | Description |  
| --- | --- |  
| content | Generates Barcode data |  
| format | Barcode format |  
| width | Generate Barcode width for printing; range 1-384 |  
| height | Generates Barcode height for printing; ranges greater than 0 |  
| align | Alignment |  
| Format Name | Description |  
| --- | --- |  
| AZTEC | |  
| CODABAR | |  
| CODE\_39 | |  
| CODE\_93 | |  
| CODE\_128 | |  
| DATA\_MATRIX | |  
| EAN\_8 | |  
| EAN\_13 | |  
| ITF | |  
| MAXICODE | |  
| PDF\_417 | |  
| QR\_CODE | |  
| RSS\_14 | |  
| RSS\_EXPANDED | |  
| UPC\_A | |

## Página 32

SmartPos API Reference Manual: 09/08/21  
UPC\_E  
UPC\_EAN\_EXTENSION  
AlignEnum  
Enumeration Name Description  
LEFT Left alignment  
RIGHT Right alignment  
CENTER Centered  
Return Value:  
SdkResult.Success success  
SdkResult.Printer\_AddImg\_Fail failure  
3.2.9 appendQRCode  
Append QR code.  
public int appendQRcode (String content, int width, int height, AlignEnum align);  
Parameters:  
Parameter Description  
content Generates QR code data  
width Generates QR code printing width; range 1-384  
height Generates QR code height for printing; ranges greater than 0  
align Alignment  
AlignEnum  
Enumeration Name Description  
LEFT Left alignment  
RIGHT Right alignment  
CENTER Centered  
Return Value:  
SdkResult.Success success  
SdkResult.Printer\_AddImg\_Fail failure  
3.2.10 appendQRcode  
Confidential 32  
| UPC\_E | |  
| --- | --- |  
| UPC\_EAN\_EXTENSION | |  
| Enumeration Name | Description |  
| --- | --- |  
| LEFT | Left alignment |  
| RIGHT | Right alignment |  
| CENTER | Centered |  
| Parameter | Description |  
| --- | --- |  
| content | Generates QR code data |  
| width | Generates QR code printing width; range 1-384 |  
| height | Generates QR code height for printing; ranges greater than 0 |  
| align | Alignment |  
| Enumeration Name | Description |  
| --- | --- |  
| LEFT | Left alignment |  
| RIGHT | Right alignment |  
| CENTER | Centered |

## Página 33

SmartPos API Reference Manual: 09/08/21  
Append QR code.  
public int appendQRcode(String content,int height, int version,  
int level,AlignEnum align);  
Parameters：  
Parameter Description  
content Generates QR code data  
height Generates QR code height for printing; ranges  
greater than 0  
version QR code version is 1-40  
level Error correction level, from low to high, 0-3  
align Alignment  
AlignEnum  
Enumeration Name Description  
LEFT Left alignment  
RIGHT Right alignment  
CENTER Centered  
Return Value:  
SdkResult.Success success  
SdkResult.Printer\_AddImg\_Fail failure  
3.2.11 feedPaper  
Feed paper.  
Public void feedPaper (int value);  
Parameters:  
Parameter Description  
value Paper length is in pixels; range of greater than or equal to 0; if the user has not set,  
the default value equals 0  
Return Value: None  
3.2.12 cutPaper  
Feed paper, the default is to feed to the end of print section.  
Confidential 33  
| Parameter | Description |  
| --- | --- |  
| content | Generates QR code data |  
| height | Generates QR code height for printing; ranges greater than 0 |  
| version | QR code version is 1-40 |  
| level | Error correction level, from low to high, 0-3 |  
| align | Alignment |  
| Enumeration Name | Description |  
| --- | --- |  
| LEFT | Left alignment |  
| RIGHT | Right alignment |  
| CENTER | Centered |  
| Parameter | Description |  
| --- | --- |  
| value | Paper length is in pixels; range of greater than or equal to 0; if the user has not set, the default value equals 0 |

## Página 34

SmartPos API Reference Manual: 09/08/21  
Public void cutPaper ();  
Parameters: None  
Return Value: None  
3.2.13 startPrint  
Start printing.  
public int startPrint (boolean rollPaperEnd, OnPrintListener listener);  
Parameters:  
Parameter Description  
rollPaperEnd Advance to the end of the paper automatically; true: yes, false: no  
listener The callback interface after printing is complete  
Return Value:  
SdkResult.Success operation is successful; listener can successfully callback  
SdkResult.Printer\_Busy printer is busy  
SdkResult.Printer\_Print\_Fail print data is empty  
SdkResult.Param\_In\_Invalid illegal Parameter  
3.2.14 setLetterSpacing  
Set the spacing between the print order lines.  
public void setLetterSpacing (int value);  
Parameters:  
Parameter Description  
value Line spacing is in pixels; the default value equals 4  
Return Value: None  
3.2.15 setGray  
Set the grayscale.  
Confidential 34  
| Parameter | Description |  
| --- | --- |  
| rollPaperEnd | Advance to the end of the paper automatically; true: yes, false: no |  
| listener | The callback interface after printing is complete |  
| Parameter | Description |  
| --- | --- |  
| value | Line spacing is in pixels; the default value equals 4 |

## Página 35

SmartPos API Reference Manual: 09/08/21  
public void setGray (GrayLevelEnum level);  
Parameters:  
Parameter Description  
level Establish gray value; if the user has not set, the default value is LEVEL\_0. The higher  
the grayscale, the darker the print font, the slower the print speed.  
GrayLevelEnum  
Enumeration Name Description  
LEVEL\_0 Primary grayscale  
LEVEL\_1 Secondary grayscale  
LEVEL\_2 Tertiary grayscale  
Return Value: None  
3.2.16 setTypeface  
Set the font type.  
public void setTypeface (Typeface typeface);  
Parameters:  
Parameter Description  
typeface Android SDK Typeface font type; user can use the default value:Typeface.  
DEFAULT  
Return Value: None  
3.3 Pinpad Class  
The password keyboard class is responsible for managing the POS password keyboard.  
Get the object of the password keyboard class:  
. PinPad pinpad = deviceEngine getPinPad () ;  
This module operates using the basic flow chart:  
Confidential 35  
| Parameter | Description |  
| --- | --- |  
| level | Establish gray value; if the user has not set, the default value is LEVEL\_0. The higher the grayscale, the darker the print font, the slower the print speed. |  
| Enumeration Name | Description |  
| --- | --- |  
| LEVEL\_0 | Primary grayscale |  
| LEVEL\_1 | Secondary grayscale |  
| LEVEL\_2 | Tertiary grayscale |  
| Parameter | Description |  
| --- | --- |  
| typeface | Android SDK Typeface font type; user can use the default value:Typeface. DEFAULT |

## Página 36

SmartPos API Reference Manual: 09/08/21  
Input password Start  
Obtain context  
Get keyboard object  
Call initpinpad  
Call inputonlinepin or  
inputofflinepin  
3.3.1 initPinPad  
Initialize the password keyboard.  
Public int initPinPad (PinPadTypeEnum ppType);  
Parameters:  
Parameter Description  
ppType PinPadTypeEnum Enum type Password keyboard type; currently only  
supports built-in password keyboard  
PinPadTypeEnum  
Enumeration Name Description  
INTERNAL Built-in password keyboard  
EXTERNAL External password keyboard  
Return Value:  
SdkResult.Success success  
SdkResult.Fail fail  
Confidential 36  
| Parameter | Description |  
| --- | --- |  
| ppType | PinPadTypeEnum Enum type Password keyboard type; currently only supports built-in password keyboard |  
| Enumeration Name | Description |  
| --- | --- |  
| INTERNAL | Built-in password keyboard |  
| EXTERNAL | External password keyboard |

## Página 37

SmartPos API Reference Manual: 09/08/21  
3.3.2 setAlgorithmMode  
Set PinPad work in DUKPT model or classical model. Default is DES model.  
Public void setAlgorithmMode(AlgorithmModeEnum algMode);  
AlgorithmModeEnum  
Enumeration Name Description  
DES DES model(Includes DES/TDES)  
SM4 PBOC use it  
DUKPT DUKPT model  
3.3.3 setCipherMode  
Set PinPad Cipher mode, default is ECB mode.  
Public void setCipherMode (CipherModeEnum cipherMode);  
CipherModeEnum  
Enumeration Name Description  
ECB  
CBC  
3.3.4 setCipherInitializationVector  
Set PinPad Cipher iv, it is used for CBC Cipher mode.  
Public void setCipherInitializationVector (byte[] iv);  
3.3.5 setPinKeyboardMode  
Set password keyboard mode, default mode is random password keyboard.  
public void setPinKeyboardMode(PinKeyboardModeEnum keyboardMode);  
Parameters:  
Parameter Description  
Confidential 37  
| Enumeration Name | Description |  
| --- | --- |  
| DES | DES model(Includes DES/TDES) |  
| SM4 | PBOC use it |  
| DUKPT | DUKPT model |  
| Enumeration Name | Description |  
| --- | --- |  
| ECB | |  
| CBC | |  
| Parameter | Description |  
| --- | --- |

## Página 38

SmartPos API Reference Manual: 09/08/21  
keyboardMode Mode, default value RANDOM  
PinKeyboardModeEnum  
Enumeration Name Description  
RANDOM Random key board  
FIXED Fixed key board  
Return Value:  
None  
3.3.6 writeMKey  
Inject the master key(plaintext key)  
Public int writeMKey (int mKeyIdx, byte [] keyData, int keyDataLen);  
Parameters:  
Parameter Description  
mKeyIdx Master Key Index 0-99  
keyData Plaintext master key data  
keyDataLen The length of the plain key range: 8,16,24  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_InValid Parameter is not legitimate  
SdkResult.PinPad\_Dstkey\_Idx\_Error wrong key index  
SdkResult.PinPad\_Key\_Len\_Error wrong key length  
SdkResult.Fail other errors  
3.3.7 writeMKey  
Inject the master key(ciper key)  
Public int writeMKey (int mKeyIdx, byte [] keyData, int keyDataLen, int decMKeyIdx);  
Parameters:  
Parameter Description  
mKeyId Master Key Index 0-199  
Confidential 38  
| keyboardMode | Mode, default value RANDOM |  
| --- | --- |  
| Enumeration Name | Description |  
| --- | --- |  
| RANDOM | Random key board |  
| FIXED | Fixed key board |  
| Parameter | Description |  
| --- | --- |  
| mKeyIdx | Master Key Index 0-99 |  
| keyData | Plaintext master key data |  
| keyDataLen | The length of the plain key range: 8,16,24 |  
| Parameter | Description |  
| --- | --- |  
| mKeyId | Master Key Index 0-199 |

## Página 39

SmartPos API Reference Manual: 09/08/21  
keyData Ciphertext master key data  
keyDataLen Ciphertext master key length should be not less than 8, and must be  
a multiple of 8 bytes  
decMKeyIdx Decrypt the master key index 0-199  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_InValid Parameter is not legitimate  
SdkResult.PinPad\_Dstkey\_Idx\_Error wrong key index  
SdkResult.PinPad\_Key\_Len\_Error wrong key length  
SdkResult.PinPad\_No\_Key\_Error key does not exist  
SdkResult.Fail other errors  
3.3.8 isKeyExist  
Whether the master key exists. It is only suitable for TMK/session key, do not suitable for DUKPT.  
Public boolean isKeyExist (int mKeyIdx);  
Parameters:  
Parameter Description  
mKeyId Master Key Index 0-199  
Return Value:  
True success, key exist  
False failure, key not exist or error.  
3.3.9 calcWKeyKCV  
Calculate the work key KCV (check value).  
Public byte [] calcWKeyKCV (int mKeyIdx, WorkKeyTypeEnum wKeyType);  
Parameters:  
Parameter Description  
mKeyIdx Master Key Index 0-199  
Confidential 39  
| keyData | Ciphertext master key data |  
| --- | --- |  
| keyDataLen | Ciphertext master key length should be not less than 8, and must be a multiple of 8 bytes |  
| decMKeyIdx | Decrypt the master key index 0-199 |  
| Parameter | Description |  
| --- | --- |  
| mKeyId | Master Key Index 0-199 |  
| Parameter | Description |  
| --- | --- |  
| mKeyIdx | Master Key Index 0-199 |

## Página 40

SmartPos API Reference Manual: 09/08/21  
wKeyType Working key type  
WorkKeyTypeEnum  
Enumeration Name Description  
PINKEY PIN key  
MACKEY MAC key  
TDKEY Track key  
ENCRYPTIONKEY Data encryption key, providing encryption  
and decryption  
Return Value:  
Success, return an array of check values  
Failure, returning null  
3.3.10 writeWKey  
Inject work key.  
Public int writeWKey (int mKeyIdx, WorkKeyTypeEnum wKeyType, byte [] keyData, int keyDataLen);  
Parameters:  
Parameter Description  
mKeyIdx Master key index number 0-199  
wKeyType Working key type  
keyData Working key cipher text data  
keyDataLen Working key cipher text length  
WorkKeyTypeEnum  
Enumeration Name Description  
PINKEY PIN key  
MACKEY MAC key  
TDKEY Track key  
ENCRYPTIONKEY Data encryption key, providing encryption  
and decryption  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_InValid Parameter is invalid  
Confidential 40  
| wKeyType | Working key type |  
| --- | --- |  
| Enumeration Name | Description |  
| --- | --- |  
| PINKEY | PIN key |  
| MACKEY | MAC key |  
| TDKEY | Track key |  
| ENCRYPTIONKEY | Data encryption key, providing encryption and decryption |  
| Parameter | Description |  
| --- | --- |  
| mKeyIdx | Master key index number 0-199 |  
| wKeyType | Working key type |  
| keyData | Working key cipher text data |  
| keyDataLen | Working key cipher text length |  
| Enumeration Name | Description |  
| --- | --- |  
| PINKEY | PIN key |  
| MACKEY | MAC key |  
| TDKEY | Track key |  
| ENCRYPTIONKEY | Data encryption key, providing encryption and decryption |

## Página 41

SmartPos API Reference Manual: 09/08/21  
SdkResult.PinPad\_Dstkey\_Idx\_Error wrong key index object; not within the scope  
SdkResult.PinPad\_Key\_Len\_Error wrong key length  
SdkResult.Fail other errors  
3.3.11 isKeyExist  
Whether the work key exists. It is only suitable for TMK/session key, do not suitable for DUKPT.  
Public boolean isKeyExist (int mKeyIdx, WorkKeyTypeEnum wKeyType);  
Parameters:  
Parameter Description  
mKeyId Master Key Index 0-199  
wKeyType Working key type  
WorkKeyTypeEnum  
Enumeration Name Description  
PINKEY PIN key  
MACKEY MAC key  
TDKEY Track key  
ENCRYPTIONKEY Data encryption key, providing encryption  
and decryption  
Return Value:  
True success ,work key exist  
False failure, work key not exist, or error  
3.3.12 calcByWkey  
public byte[] calcByWKey(int mKeyIdx, WorkKeyTypeEnum wKeyType, byte[] data, int dataLen,  
CalcModeEnum calcMode);  
Parameters:  
Parameter Description  
mKeyIdx Master Key Index 0-199  
wKeyType Working key type  
data Input data  
dataLen Data length  
Confidential 41  
| Parameter | Description |  
| --- | --- |  
| mKeyId | Master Key Index 0-199 |  
| wKeyType | Working key type |  
| Enumeration Name | Description |  
| --- | --- |  
| PINKEY | PIN key |  
| MACKEY | MAC key |  
| TDKEY | Track key |  
| ENCRYPTIONKEY | Data encryption key, providing encryption and decryption |  
| public byte[] calcByWKey(int mKeyIdx, WorkKeyTypeEnum wKeyType, byte[] data, int dataLen, |  
| --- |  
| CalcModeEnum calcMode); |  
| Parameter | Description |  
| --- | --- |  
| mKeyIdx | Master Key Index 0-199 |  
| wKeyType | Working key type |  
| data | Input data |  
| dataLen | Data length |

## Página 42

SmartPos API Reference Manual: 09/08/21  
calcMode encryption or decryption  
CalcModeEnum  
Enumeration Name Description  
ENCRYPT ENCRYPT mode  
DECRYPT DECRYPT mode  
3.3.13 desByWKey  
Work key encryption and decryption.  
Public byte [] desByWKey (int mKeyIdx, WorkKeyTypeEnum wKeyType, byte [] data, int dataLen,  
DesKeyModeEnum keyMode, CalcModeEnum calcMode );  
Parameters:  
Parameter Description  
mKeyIdx Master Key Index 0-199  
wKeyType Working key type  
data Input data  
dataLen Data length  
keyMode Key Mode  
calcMode Calc Mode  
WorkKeyTypeEnum  
Enumeration Name Description  
PINKEY PIN key  
MACKEY MAC key  
TDKEY Track key  
ENCRYPTIONKEY Data encryption key, providing encryption  
and decryption  
DesKeyModeEnum  
Enumeration Name Description  
KEY\_ALL  
KEY\_FIRST Specify the type of algorithm, key double  
length is used to  
do the first 8 bytes DES operation  
KEY\_LAST Specify the type of algorithm, key double  
length is used to  
do the last 8 bytes DES operation  
Confidential 42  
| calcMode | encryption or decryption | None |  
| --- | --- | --- |  
| CalcModeEnum | None | None |  
| Enumeration Name | None | Description |  
| ENCRYPT | None | ENCRYPT mode |  
| DECRYPT | None | DECRYPT mode |  
| | None | None |  
| Parameter | Description |  
| --- | --- |  
| mKeyIdx | Master Key Index 0-199 |  
| wKeyType | Working key type |  
| data | Input data |  
| dataLen | Data length |  
| keyMode | Key Mode |  
| calcMode | Calc Mode |  
| Enumeration Name | Description |  
| --- | --- |  
| PINKEY | PIN key |  
| MACKEY | MAC key |  
| TDKEY | Track key |  
| ENCRYPTIONKEY | Data encryption key, providing encryption and decryption |  
| Enumeration Name | Description |  
| --- | --- |  
| KEY\_ALL | |  
| KEY\_FIRST | Specify the type of algorithm, key double length is used to do the first 8 bytes DES operation |  
| KEY\_LAST | Specify the type of algorithm, key double length is used to do the last 8 bytes DES operation |

## Página 43

SmartPos API Reference Manual: 09/08/21  
CalcModeEnum  
Enumeration Name Description  
ENCRYPT Encrypt  
DECRYPT Decrypt  
Return Value:  
Success returns the computed array  
Failure, returns null  
3.3.14 encryptTrackData  
Use TDK work key to Encrypt track data.  
public byte[] encryptTrackData(int mKeyId, byte[] trackData, int trackDataLen);  
Parameters:  
Parameter Description  
mKeyId Master key index 0-199  
trackData Track data  
trackDataLen Len of track data  
Return Value:  
Success returns the computed array  
Failure, returns null  
3.3.15 calcMac  
Use TAK work key to Calculate MAC.  
Public byte [] calcMac (int mKeyIdx, MacAlgorithmModeEnum macAlgMode, byte [] data);  
Parameters:  
Parameter Description  
mKeyIdx Master Key Index 0-199  
macAlgMode MAC algorithm approach  
data Input data  
Confidential 43  
| Enumeration Name | Description |  
| --- | --- |  
| ENCRYPT | Encrypt |  
| DECRYPT | Decrypt |  
| Parameter | Description |  
| --- | --- |  
| mKeyId | Master key index 0-199 |  
| trackData | Track data |  
| trackDataLen | Len of track data |  
| Parameter | Description |  
| --- | --- |  
| mKeyIdx | Master Key Index 0-199 |  
| macAlgMode | MAC algorithm approach |  
| data | Input data |

## Página 44

SmartPos API Reference Manual: 09/08/21  
MacAlgorithmModeEnum  
Enumeration Name Description  
ECB ECB Algorithm  
X99 ANSI X9.9 Encryption Algorithm  
X919 ANSI X9.19 Encryption Algorithm  
Return Value:  
Success returns the computed array  
Failure, returning null  
3.3.16 calcMac  
Calculate MAC.  
Public byte [] calcMac (int mKeyIdx, MacAlgorithmModeEnum macAlgMode, DesKeyModeEnum  
keyMode , byte [] data);  
Parameters:  
Parameter Description  
mKeyIdx Master Key Index 0-199  
macAlgMode MAC algorithm approach  
desAlgMode Algorithm type  
data Input data  
MacAlgorithmModeEnum  
Enumeration Name Description  
ECB ECB Algorithm  
X99 ANSI X9.9 Encryption Algorithm  
X919 ANSI X9.19 Encryption Algorithm  
DesKeyModeEnum  
Enumeration Name Description  
KEY\_ALL  
KEY\_FIRST Do des with the first 8 bytes of the key  
KEY\_LAST Do des with the last 8 bytes of the key  
Confidential 44  
| Enumeration Name | Description |  
| --- | --- |  
| ECB | ECB Algorithm |  
| X99 | ANSI X9.9 Encryption Algorithm |  
| X919 | ANSI X9.19 Encryption Algorithm |  
| Parameter | Description |  
| --- | --- |  
| mKeyIdx | Master Key Index 0-199 |  
| macAlgMode | MAC algorithm approach |  
| desAlgMode | Algorithm type |  
| data | Input data |  
| Enumeration Name | Description |  
| --- | --- |  
| ECB | ECB Algorithm |  
| X99 | ANSI X9.9 Encryption Algorithm |  
| X919 | ANSI X9.19 Encryption Algorithm |  
| Enumeration Name | Description |  
| --- | --- |  
| KEY\_ALL | |  
| KEY\_FIRST | Do des with the first 8 bytes of the key |  
| KEY\_LAST | Do des with the last 8 bytes of the key |

## Página 45

SmartPos API Reference Manual: 09/08/21  
Return Value:  
Success returns the computed array  
Failure, returning null  
3.3.17 calcMac(DUKPT)  
byte[] calcMac(int mKeyIdx, MacAlgorithmModeEnum macAlgMode, DukptKeyModeEnum keyMode,  
byte[] data);  
Parameters:  
Parameter Description  
mKeyId Key Index 0-19(DUKPT only support 0-19 key index)  
macAlgMode Mac Alg mode  
keyMode Key mode  
data Data, lack of an integer multiple of 8, after the meeting 0x00 or …the fill  
data is decided by application  
MacAlgorithmModeEnum  
Enumeration Name Description  
ECB  
CBC  
X919  
MAC9606  
DukptKeyModeEnum  
Enumeration Name Description  
REQUEST Request mode  
RESPONSE Response mode  
3.3.18 encryptByMKey  
Master key encryption.  
Public byte [] encryptByMKey (int mKeyId, byte [] data, int dataLen);  
Confidential 45  
| byte[] calcMac(int mKeyIdx, MacAlgorithmModeEnum macAlgMode, DukptKeyModeEnum keyMode, |  
| --- |  
| byte[] data); |  
| |  
| Parameter | Description | None |  
| --- | --- | --- |  
| mKeyId | Key Index 0-19(DUKPT only support 0-19 key index) | None |  
| macAlgMode | Mac Alg mode | None |  
| keyMode | Key mode | None |  
| data | Data, lack of an integer multiple of 8, after the meeting 0x00 or …the fill data is decided by application | None |  
| | None | None |  
| MacAlgorithmModeEnum | None | None |  
| Enumeration Name | None | Description |  
| ECB | None | |  
| CBC | None | |  
| X919 | None | |  
| MAC9606 | None | |  
| | None | None |  
| | None | None |  
| DukptKeyModeEnum | None | None |  
| Enumeration Name | None | Description |  
| REQUEST | None | Request mode |  
| RESPONSE | None | Response mode |  
| | None | |  
| | None | |

## Página 46

SmartPos API Reference Manual: 09/08/21  
Parameters:  
Parameter Description  
mKeyId Master Key Index 0-199  
data Data, lack of an integer multiple of 8, after the meeting 0x00  
dataLen Length, maximum 1024 bytes  
desAlgMode DES algorithm type  
DesAlgorithmModeEnum  
Return Value:  
Success returns the computed array  
Failure, returning null  
3.3.19 setPinpadLayout  
Set the password keyboard layout. After this method is called, when inputOnlinePin or  
inputOfflinePin is called , the layout of the password keyboard will be drawned by the  
app layer itself, without using the system default password keyboard interface.  
public byte[] setPinpadLayout(PinpadLayoutEntity pinpadLayout);  
Parameters:  
Parameter Description  
pinpadLayout Coordinates of 10 digital keys and 3 function  
keys  
PinpadLayoutEntity  
attribute Description  
Rect key1 The coordinate of the number key "1"  
Rect key2 The coordinate of the number key "2"  
Rect key3 The coordinate of the number key "3"  
Rect key4 The coordinate of the number key "4"  
Rect key5 The coordinate of the number key "5"  
Rect key6 The coordinate of the number key "6"  
Rect key7 The coordinate of the number key "7"  
Rect key8 The coordinate of the number key "8"  
Rect key9 The coordinate of the number key "9"  
Rect key10 The coordinate of the number key "0"  
Confidential 46  
| Parameter | Description |  
| --- | --- |  
| mKeyId | Master Key Index 0-199 |  
| data | Data, lack of an integer multiple of 8, after the meeting 0x00 |  
| dataLen | Length, maximum 1024 bytes |  
| desAlgMode | DES algorithm type |  
| Parameter | Description |  
| --- | --- |  
| pinpadLayout | Coordinates of 10 digital keys and 3 function keys |  
| attribute | Description |  
| --- | --- |  
| Rect key1 | The coordinate of the number key "1" |  
| Rect key2 | The coordinate of the number key "2" |  
| Rect key3 | The coordinate of the number key "3" |  
| Rect key4 | The coordinate of the number key "4" |  
| Rect key5 | The coordinate of the number key "5" |  
| Rect key6 | The coordinate of the number key "6" |  
| Rect key7 | The coordinate of the number key "7" |  
| Rect key8 | The coordinate of the number key "8" |  
| Rect key9 | The coordinate of the number key "9" |  
| Rect key10 | The coordinate of the number key "0" |

## Página 47

SmartPos API Reference Manual: 09/08/21  
Rect keyCancel The coordinate of the key "cancel"  
Rect keyConfirm The coordinate of the key "confirm"  
Rect keyClear The coordinate of the key "clear"  
Return Value:  
Byte[] Returns 0-9 digits for 10 key successfully  
null Failed  
3.3.20 inputOnlinePin  
Enter the online PIN.  
Public int inputOnlinePin (int [] pinLen, int timeout, byte [] panBlock, int mKeyId,  
PinAlgorithmModeEnum pinAlgMode, OnPinPadInputListener listener);  
Parameters:  
Parameter Description  
pinLen The length of the support, such  
as{0x00,0x04,0x05,0x06,0x07,0x08,0x09,0x0a, 0x0b, 0x0c}  
timeout Enter a timeout in seconds; recommended value 60  
panBlock Card number, asc coding  
mKeyId Master Key Index 0-199(if DUKPT, is 0-19)  
pinAlgMode PIN encryption algorithm mode  
listener Monitor callback interface  
PinAlgorithmModeEnum  
Enumeration Name Description  
ISO9564FMT1 Format 0, Currently only supports Format 0  
ISO9564FMT2 Format 0, Currently only supports Format 0  
ISO9564FMT3 Format 0, Currently only supports Format 0  
Return Value:  
SdkResult.Success successful execution listener callback interface  
SdkResult.Param\_In\_InValid illegal Parameter  
SdkResult.Fail other errors  
3.3.21 inputOfflinePin  
Confidential 47  
| Rect keyCancel | The coordinate of the key "cancel" |  
| --- | --- |  
| Rect keyConfirm | The coordinate of the key "confirm" |  
| Rect keyClear | The coordinate of the key "clear" |  
| Parameter | Description |  
| --- | --- |  
| pinLen | The length of the support, such as{0x00,0x04,0x05,0x06,0x07,0x08,0x09,0x0a, 0x0b, 0x0c} |  
| timeout | Enter a timeout in seconds; recommended value 60 |  
| panBlock | Card number, asc coding |  
| mKeyId | Master Key Index 0-199(if DUKPT, is 0-19) |  
| pinAlgMode | PIN encryption algorithm mode |  
| listener | Monitor callback interface |  
| Enumeration Name | Description |  
| --- | --- |  
| ISO9564FMT1 | Format 0, Currently only supports Format 0 |  
| ISO9564FMT2 | Format 0, Currently only supports Format 0 |  
| ISO9564FMT3 | Format 0, Currently only supports Format 0 |

## Página 48

SmartPos API Reference Manual: 09/08/21  
Enter the offline PIN(offline plaintext pin, or offline cipher pin).  
Public int inputOfflinePin (int [] pinLen, int timeout, OnPinPadInputListener listener);  
Parameters:  
Parameter Description  
pinLen The length of the support, such  
as{0x00,0x04,0x05,0x06,0x07,0x08,0x09,0x0a, 0x0b, 0x0c}  
timeout Enter a timeout in seconds; recommended value 60  
listener Monitor callback interface  
Return Value:  
SdkResult.Success successful execution listener callback interface  
SdkResult.Param\_In\_InValid illegal Parameter  
SdkResult.Fail other errors  
3.3.22 isInputting  
Whether the keyboard is typing.  
Public boolean isInputting ();  
Parameters: None  
Return Value:  
True success  
False failure  
3.3.23 cancelInput  
Cancel the keyboard input.  
Public void cancelInput ();  
Parameters: None  
Return Value: None  
3.3.24 format  
Format the key area.  
Confidential 48  
| Parameter | Description |  
| --- | --- |  
| pinLen | The length of the support, such as{0x00,0x04,0x05,0x06,0x07,0x08,0x09,0x0a, 0x0b, 0x0c} |  
| timeout | Enter a timeout in seconds; recommended value 60 |  
| listener | Monitor callback interface |

## Página 49

SmartPos API Reference Manual: 09/08/21  
Public boolean format ();  
Parameters: None  
Return Value:  
True success  
False failure  
3.3.25 deleteMKey  
Clear the master key.  
Public boolean deleteMKey (int mKeyId);  
Parameters:  
Parameter Description  
mKeyId Master Key Index 0-199  
Return Value:  
True success  
False failure  
3.3.26 dukptKeyInject  
Inject BDK(or IPEK) and KSN for DUKPT.  
Public int dukptKeyInject(int mKeyIdx, DukptKeyTypeEnum keyType, byte[] keyData, int keyDataLen,  
byte[] ksn);  
Parameters:  
Parameter Description  
mKeyIdx Key Index 0-19  
keyType Key type  
keyData BDK  
keyDataLen BDK length  
ksn KSN  
DukptKeyTypeEnum  
Enumeration Name Description  
BDK BDK  
Confidential 49  
| Parameter | Description |  
| --- | --- |  
| mKeyId | Master Key Index 0-199 |  
| Public int dukptKeyInject(int mKeyIdx, DukptKeyTypeEnum keyType, byte[] keyData, int keyDataLen, |  
| --- |  
| byte[] ksn); |  
| Parameter | Description |  
| --- | --- |  
| mKeyIdx | Key Index 0-19 |  
| keyType | Key type |  
| keyData | BDK |  
| keyDataLen | BDK length |  
| ksn | KSN |  
| Enumeration Name | Description |  
| --- | --- |  
| BDK | BDK |

## Página 50

SmartPos API Reference Manual: 09/08/21  
IPEK IPEK  
Return value:  
SdkResult.Success,  
SdkResult.Fail,  
SdkResult.PinPad\_KeyIdx\_Error,  
SdkResult.Param\_In\_Invalid  
3.3.27 dukptCipherKeyInject  
Inject cipher BDK(or IPEK) and KSN for DUKPT.  
Public int dukptCipherKeyInject(int dukptKeyIdx, int decKeyIdx, WorkKeyTypeEnum workKeyTypeEnum,  
DukptKeyTypeEnum keyType, CalcModeEnum calcModeEnum, byte[] keyCipherData, byte[] ksn);  
Parameters:  
Parameter Description  
dukptKeyIdx Key Index 0-19  
decKeyIdx Decrypt Key index 0-199(use MK/SK key to decrypt the cipher  
BDK/IPEK and inject)  
workKeyTypeEnum If workKeyTypeEnum != null, use work key to decrypt and inject cipher  
BDK/IPEK ;  
If workKeyTypeEnum == null, use master key to decrypt and inject  
cipher BDK/IPEK ;  
keyType BDK/IPEK  
calcModeEnum Encryption or decryption  
keyCipherData Cipher BDK/IPEK  
ksn KSN  
WorkKeyTypeEnum  
Enumeration Name Description  
PINKEY PIN key  
MACKEY MAC key  
TDKEY Track key  
ENCRYPTIONKEY Data encryption key, providing encryption  
and decryption  
Confidential 50  
| IPEK | IPEK |  
| --- | --- |  
| Public int dukptCipherKeyInject(int dukptKeyIdx, int decKeyIdx, WorkKeyTypeEnum workKeyTypeEnum, |  
| --- |  
| DukptKeyTypeEnum keyType, CalcModeEnum calcModeEnum, byte[] keyCipherData, byte[] ksn); |  
| Parameter | Description |  
| --- | --- |  
| dukptKeyIdx | Key Index 0-19 |  
| decKeyIdx | Decrypt Key index 0-199(use MK/SK key to decrypt the cipher BDK/IPEK and inject) |  
| workKeyTypeEnum | If workKeyTypeEnum != null, use work key to decrypt and inject cipher BDK/IPEK ; If workKeyTypeEnum == null, use master key to decrypt and inject cipher BDK/IPEK ; |  
| keyType | BDK/IPEK |  
| calcModeEnum | Encryption or decryption |  
| keyCipherData | Cipher BDK/IPEK |  
| ksn | KSN |  
| Enumeration Name | Description |  
| --- | --- |  
| PINKEY | PIN key |  
| MACKEY | MAC key |  
| TDKEY | Track key |  
| ENCRYPTIONKEY | Data encryption key, providing encryption and decryption |

## Página 51

SmartPos API Reference Manual: 09/08/21  
DukptKeyTypeEnum  
Enumeration Name Description  
BDK BDK  
IPEK IPEK  
CalcModeEnum  
Enumeration Name Description  
ENCRYPT ENCRYPT mode  
DECRYPT DECRYPT mode  
Return value:  
SdkResult.Success,  
SdkResult.Fail,  
SdkResult.PinPad\_KeyIdx\_Error,  
SdkResult.Param\_In\_Invalid  
3.3.28 dukptKsnIncrease  
Use it to increase ksn, otherwise the ksn will not change.  
Public void dukptKsnIncrease(int mKeyIdx);  
Parameters:  
Parameter Description  
mKeyIdx Key Index 0-19  
3.3.29 dukptCurrentKsn  
Get current Ksn value.  
Public byte[] dukptCurrentKsn(int mKeyIdx);  
Parameters:  
Parameter Description  
mKeyIdx Key Index 0-19  
Confidential 51  
| Enumeration Name | Description |  
| --- | --- |  
| BDK | BDK |  
| IPEK | IPEK |  
| CalcModeEnum | None |  
| --- | --- |  
| Enumeration Name | Description |  
| ENCRYPT | ENCRYPT mode |  
| DECRYPT | DECRYPT mode |  
| Parameter | Description |  
| --- | --- |  
| mKeyIdx | Key Index 0-19 |  
| Parameter | Description |  
| --- | --- |  
| mKeyIdx | Key Index 0-19 |

## Página 52

SmartPos API Reference Manual: 09/08/21  
3.3.30 dukptEncrypt  
Encrypt data in dukpt model.  
Public byte[] dukptEncrypt(int mKeyIdx, DukptKeyModeEnum keyMode, byte[] data, int dataLen);  
Parameters:  
Parameter Description  
mKeyIdx Key Index 0-19  
keyMode Encrypt model  
data Encrypt data  
dataLen Encrypt data’s length  
DukptKeyModeEnum  
Enumeration Name Description  
REQUEST  
RESPONSE  
Return value:  
Bytes Array,  
Null  
3.3.31 dukptEncrypt  
Encrypt data in dukpt model.  
Public byte[] dukptEncrypt(int mKeyIdx, DukptKeyModeEnum keyMode, byte[] data, int dataLen,  
DesAlgorithmModeEnum desMode, byte[] iv);  
Parameters:  
Parameter Description  
mKeyIdx Key Index 0-19  
keyMode Encrypt model  
data Encrypt data  
dataLen Encrypt data’s length  
desMode Use ECB or CBC  
iv Iv for CBC mode  
DukptKeyModeEnum  
Enumeration Name Description  
Confidential 52  
| Parameter | Description | None |  
| --- | --- | --- |  
| mKeyIdx | Key Index 0-19 | None |  
| keyMode | Encrypt model | None |  
| data | Encrypt data | None |  
| dataLen | Encrypt data’s length | None |  
| DukptKeyModeEnum | None | None |  
| Enumeration Name | None | Description |  
| REQUEST | None | |  
| RESPONSE | None | |  
| Public byte[] dukptEncrypt(int mKeyIdx, DukptKeyModeEnum keyMode, byte[] data, int dataLen, |  
| --- |  
| DesAlgorithmModeEnum desMode, byte[] iv); |  
| Parameter | Description | None |  
| --- | --- | --- |  
| mKeyIdx | Key Index 0-19 | None |  
| keyMode | Encrypt model | None |  
| data | Encrypt data | None |  
| dataLen | Encrypt data’s length | None |  
| desMode | Use ECB or CBC | None |  
| iv | Iv for CBC mode | None |  
| DukptKeyModeEnum | None | None |  
| Enumeration Name | None | Description |

## Página 53

SmartPos API Reference Manual: 09/08/21  
REQUEST  
RESPONSE  
DesAlgorithmModeEnum  
Enumeration Name Description  
ECB  
CBC  
Return value:  
Bytes Array,  
Null  
3.4 Scaner#1(default UI)  
Camera scan code class is responsible for managing POS camera; must be initialized before use.  
Get the object of the camera scan class:  
Scanner scanner = deviceEngine getScanner();  
The following table shows the Return Values supported by the method of the camera sweep class:  
Constant Name Constant Value Description  
Scanner\_Base\_Error -2000  
Scanner\_Customer\_Exit Scanner\_Base\_Error - 1 Active user exit  
Scanner\_Other\_Error Scanner\_Base\_Error - 2 Scan code fails  
This module operates using the basic flow chart:  
Confidential 53  
| REQUEST | |  
| --- | --- |  
| RESPONSE | |  
| DesAlgorithmModeEnum | None |  
| Enumeration Name | Description |  
| ECB | |  
| CBC | |  
| Constant Name | Constant Value | Description |  
| --- | --- | --- |  
| Scanner\_Base\_Error | -2000 | |  
| Scanner\_Customer\_Exit | Scanner\_Base\_Error - 1 | Active user exit |  
| Scanner\_Other\_Error | Scanner\_Base\_Error - 2 | Scan code fails |

## Página 54

SmartPos API Reference Manual: 09/08/21  
Start  
Get context  
Get scanner object  
Use initscanner  
N  
Success  
Y  
Start scan  
Stop scan  
End  
3.4.1 initScanner  
Initialize the scan configuration.  
Public int initScanner (ScannerCfgEntity cfgEntity, OnScannerListener listener);  
Parameters:  
Confidential 54

## Página 55

SmartPos API Reference Manual: 09/08/21  
Parameter Description  
cfgEntity Initialize the configuration  
listener Callback interface  
ScannerCfgEntity  
Attributes Description  
boolean isUsedFrontCcd Whether to use the front camera, if only  
back camera, then open the back camera by  
default  
boolean isBulkMode Whether continuous scan mode, open the  
scan after the success of the scan does not  
exit the interface  
int interval Continuous scan code interval, in  
milliseconds; default 1000  
boolean isAutoFocus Whether it is auto focus  
boolean isNeedPreview Whether it is need pre-view  
Bundle mBundle Use bundle to transfer parameter to  
customized the Scanner UI  
Key Description  
boolean showBar If show Bar  
boolean showBack Whether show the back button  
boolean showTitle Whether show the Title text  
boolean showSwitch Whether show the button for switching  
front and back camera  
boolean showMenu Whether show the Menu  
String Title Customized the title text  
int TitleSize The size of the Title text  
string ScanTip Customized the Scan tip text  
int TipSize The size of the tip text  
Confidential 55  
| Parameter | Description |  
| --- | --- |  
| cfgEntity | Initialize the configuration |  
| listener | Callback interface |  
| Attributes | Description |  
| --- | --- |  
| boolean isUsedFrontCcd | Whether to use the front camera, if only back camera, then open the back camera by default |  
| boolean isBulkMode | Whether continuous scan mode, open the scan after the success of the scan does not exit the interface |  
| int interval | Continuous scan code interval, in milliseconds; default 1000 |  
| boolean isAutoFocus | Whether it is auto focus |  
| boolean isNeedPreview | Whether it is need pre-view |  
| Bundle mBundle Key boolean showBar boolean showBack boolean showTitle boolean showSwitch boolean showMenu String Title int TitleSize string ScanTip int TipSize | Use bundle to transfer parameter to customized the Scanner UI Description If show Bar Whether show the back button Whether show the Title text Whether show the button for switching front and back camera Whether show the Menu Customized the title text The size of the Title text Customized the Scan tip text The size of the tip text |

## Página 56

SmartPos API Reference Manual: 09/08/21  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.4.2 startScan  
start scan  
Public int startScan (int timeout, OnScannerListener listener);  
Parameters:  
Parameter Description  
timeout Scan code timeout in seconds; recommended value 60  
listener Callback interface  
Return Value:  
SdkResult.Sucess success  
SdkResult.Fail failure  
SdkResult.Param\_In\_Invalid illegal Parameter  
3.4.3 stopScan  
Stop scanning.  
Public void stopScan();  
Parameters: None  
Return Value: None  
3.4.4 decode  
decode the image  
public String decode(byte[] imageData, int imageWidth, int imageHeight);  
Parameters：  
Confidential 56  
| Parameter | Description |  
| --- | --- |  
| timeout | Scan code timeout in seconds; recommended value 60 |  
| listener | Callback interface |

## Página 57

SmartPos API Reference Manual: 09/08/21  
Parameter Description  
imageData Image, date type is YUV420SP  
imageWidth Image width  
imageHeight Image height  
Return Value:  
Failed : None  
Success: decode result  
3.5 Scanner#2(customizable UI)  
The scanning UI can be customized. For details, see demo  
Get the object of camera code scanning class:  
.  
Scanner scanner = deviceEngine getScanner2() ;  
3.5.1 initScanner  
Initialize scan configuration  
public void initScanner(ScannerCfgEntity cfgEntity, Set enableSymbols);  
Parameters:  
Parameters: Description  
Initialize the configuration  
cfgEntity  
enableSymbols Set supported code type  
ScannerCfgEntity  
Attributes  
Description  
boolean Whether to use the front camera or not. If only the back camera is used,  
isUsedFrontCcd the back camera will be turned on by default  
Confidential 57  
| Parameter | Description |  
| --- | --- |  
| imageData | Image, date type is YUV420SP |  
| imageWidth | Image width |  
| imageHeight | Image height |  
| Parameters: | Description |  
| --- | --- |  
| cfgEntity | Initialize the configuration |  
| enableSymbols | Set supported code type |  
| Attributes | Description |  
| --- | --- |  
| boolean isUsedFrontCcd | Whether to use the front camera or not. If only the back camera is used, the back camera will be turned on by default |

## Página 58

SmartPos API Reference Manual: 09/08/21  
boolean isBulkMode Continuous code scanning mode. If it is enabled, the code scanning  
interface will not exit after the code scanning succeeds  
int interval Continuous code scanning interval, unit: Ms default value: 1000  
boolean isAutoFocus Auto focus or not  
boolean isNeedPreview Preview required, default required  
Bundle mBundle User defined interface display settings, you can set the following table key  
values through bundle.  
Key Type description  
showBar boolean Show title bar or not  
BarColor int Title bar background color  
showBack boolean Show back button or not  
showTitle boolean Display title text or not  
showSwitch boolean is front/back camera switch button  
displayed  
showMenu boolean Show menu or not  
Title String Custom title text  
TitleSize int Title Text Size  
TitleColor int Title Text Color  
MaskColor int Preview mask color  
AngleColor int Color of four corners of code box  
FrameColor int Frame color  
SlideColor int Scanline color  
ScanTip int Custom prompt text  
Confidential 58  
| | |  
| --- | --- |  
| boolean isBulkMode | Continuous code scanning mode. If it is enabled, the code scanning interface will not exit after the code scanning succeeds |  
| int interval | Continuous code scanning interval, unit: Ms default value: 1000 |  
| boolean isAutoFocus | Auto focus or not |  
| boolean isNeedPreview | Preview required, default required |  
| Bundle mBundle | User defined interface display settings, you can set the following table key values through bundle. Key Type description showBar boolean Show title bar or not BarColor int Title bar background color showBack boolean Show back button or not showTitle boolean Display title text or not showSwitch boolean is front/back camera switch button displayed showMenu boolean Show menu or not Title String Custom title text TitleSize int Title Text Size TitleColor int Title Text Color MaskColor int Preview mask color AngleColor int Color of four corners of code box FrameColor int Frame color SlideColor int Scanline color ScanTip int Custom prompt text |

## Página 59

SmartPos API Reference Manual: 09/08/21  
TipColor int Prompt text color  
TipSize int Prompt text size  
Pendant String Image mount path  
return value : none  
3.5.2 getBestPreviewSize  
Get the best preview resolution  
public Size getBestPreviewSize();  
parameter：none  
return value：Size  
3.5.3 setSurface  
Set the preview surface. If not, there will be no preview scanning. Generally, this method is  
used to call getbestpreviewsize() to return the resolution supported by the camera, and then set  
it  
public void setSurface(Surface surface, int width, int height);  
parameter：  
parameter Description  
surface  
width width  
height height  
return value : none  
Confidential 59  
| | TipColor int Prompt text color TipSize int Prompt text size Pendant String Image mount path |  
| --- | --- |  
| | |  
| | |  
| parameter | Description |  
| --- | --- |  
| surface | |  
| width | width |  
| height | height |

## Página 60

SmartPos API Reference Manual: 09/08/21  
3.5.4 start  
Start camera scanning  
public void start(OnScannerListener listener);  
parameter：  
parameter Description  
listener Decode listener  
return value : none  
3.5.5 stop  
Stop scanning code and call when user initiatively exits.  
public void stop();  
parameter：none  
return value : none  
3.5.6 switchCamera  
Before and after the switch, the camera is called after start. If you want to set up which camera  
to use from the beginning, please send it in initScanner configuration.  
public void switchCamera(boolean usedFrontCcd);  
parameter：  
parameter Description  
usedFrontCcd Front camera or not  
return value : none  
3.5.7 flashTrigger  
Confidential 60  
| parameter | Description |  
| --- | --- |  
| listener | Decode listener |  
| parameter | Description |  
| --- | --- |  
| usedFrontCcd | Front camera or not |

## Página 61

SmartPos API Reference Manual: 09/08/21  
Turn on the flash and call after start.  
public void flashTrigger(boolean on);  
parameter：  
parameter Description  
on Turn on flash or not  
return value : none  
3.5.8 focusTrigger  
Open autofocus and call after start.  
public void focusTrigger(boolean auto);  
parameter：  
parameter Description  
auto Turn on auto connect focus  
return value : none  
3.5.9 setZoom  
Set up an enlarged preview and call it after start.  
public void setZoom(float scale);  
Parameter term：  
Parameter Description  
scale 0f~1.0f, restore default when 0  
return value : none  
3.6 Card Reader Class  
Confidential 61  
| parameter | Description |  
| --- | --- |  
| on | Turn on flash or not |  
| parameter | Description |  
| --- | --- |  
| auto | Turn on auto connect focus |  
| Parameter | Description |  
| --- | --- |  
| scale | 0f~1.0f, restore default when 0 |

## Página 62

SmartPos API Reference Manual: 09/08/21  
Card reader is responsible for managing the POS card reader (Note: 1, the user can take the initiative to  
stop the card operation; 2, find card operation automatically stop after the card is found).  
Get the object of the reader class:  
CardReader reader = deviceEngine getCardReader (). ;  
This module operates using the basic flow chart:  
Confidential 62

## Página 63

SmartPos API Reference Manual: 09/08/21  
Start  
Obtain context  
Get cardreader object  
Call searchcard to detect card  
Use stopSearch to stop search  
User  
Timeout  
canceled  
Check card  
Has card  
after cardreader returned,  
use iscardexist to detect if  
card was removed  
End  
3.6.1 searchCard  
Confidential 63

## Página 64

SmartPos API Reference Manual: 09/08/21  
Open the corresponding card reader, check the corresponding card slot has a card.  
public int searchCard(HashSet slotTypes,int timeout, OnCardInfoListener listener);  
Parameters:  
Parameter Description  
slotTypes Slot enumerated type CardSlotTypeEnum; supports a variety of  
combinations of slots  
timeout Timeout in seconds; recommended value 60  
listener Callback interface OnCardInfoListener  
CardSlotTypeEnum  
Enumeration Name Description  
ICC1 Default IC card slot  
ICC2 Unavailable  
ICC3 Unavailable  
PSAM1 PSAM slot 1  
PSAM2 PSAM slot 2  
PSAM3 Unavailable  
RF Non-access card slot  
SWIPE Magnetic stripe card slot  
Return Value:  
SdkResult.Success successful execution listener callback interface  
SdkResult.Fail other errors  
3.6.2 stopSearch  
Turn off the corresponding card reader and stop detecting if there is a card in the card slot.  
Public void stopSearch();  
Parameters: None  
Return Value: None  
3.6.3 isCardExist  
Confidential 64  
| Parameter | Description |  
| --- | --- |  
| slotTypes | Slot enumerated type CardSlotTypeEnum; supports a variety of combinations of slots |  
| timeout | Timeout in seconds; recommended value 60 |  
| listener | Callback interface OnCardInfoListener |  
| | |  
| Enumeration Name | Description |  
| --- | --- |  
| ICC1 | Default IC card slot |  
| ICC2 | Unavailable |  
| ICC3 | Unavailable |  
| PSAM1 | PSAM slot 1 |  
| PSAM2 | PSAM slot 2 |  
| PSAM3 | Unavailable |  
| RF | Non-access card slot |  
| SWIPE | Magnetic stripe card slot |

## Página 65

SmartPos API Reference Manual: 09/08/21  
When the card reader operation is finished, call the card to check whether the contact IC card is pulled  
out or if there is a non-card access card in the slot.  
Public boolean isCardExist ( CardSlotTypeEnum slotTypes);  
Parameters:  
Parameter Description  
slotTypes Slot enumerated type CardSlotTypeEnum  
CardSlotTypeEnum  
Enumeration Name Description  
ICC1 Default IC card slot  
ICC2 Unavailable  
ICC3 Unavailable  
PSAM1 PSAM slot 1  
PSAM2 PSAM slot 2  
PSAM3 Unavailable  
RF Non-access card slot  
SWIPE Magnetic stripe card slot  
Return Value:  
True exists  
False does not exist  
3.6.4 open  
Open the specified slot, and if you have already called searchCard to find the card, you do not need to  
call open again  
public void open(CardSlotTypeEnum cardSlotType);  
Parameters:  
Parameter Description  
cardSlotType CardSlotTypeEnum  
CardSlotTypeEnum  
Enumeration Name Description  
ICC1 Default IC card slot  
ICC2 Unavailable  
Confidential 65  
| Parameter | Description |  
| --- | --- |  
| slotTypes | Slot enumerated type CardSlotTypeEnum |  
| Enumeration Name | Description |  
| --- | --- |  
| ICC1 | Default IC card slot |  
| ICC2 | Unavailable |  
| ICC3 | Unavailable |  
| PSAM1 | PSAM slot 1 |  
| PSAM2 | PSAM slot 2 |  
| PSAM3 | Unavailable |  
| RF | Non-access card slot |  
| SWIPE | Magnetic stripe card slot |  
| Parameter | Description |  
| --- | --- |  
| cardSlotType | CardSlotTypeEnum |  
| Enumeration Name | Description |  
| --- | --- |  
| ICC1 | Default IC card slot |  
| ICC2 | Unavailable |

## Página 66

SmartPos API Reference Manual: 09/08/21  
ICC3 Unavailable  
PSAM1 PSAM slot 1  
PSAM2 PSAM slot 2  
PSAM3 Unavailable  
RF Non-access card slot  
SWIPE Magnetic stripe card slot  
Return Value: None  
3.6.5 close  
Close the specified slot.  
public void close(CardSlotTypeEnum cardSlotType);  
Parameters:  
Parameter Description  
cardSlotType CardSlotTypeEnum  
CardSlotTypeEnum  
Enumeration Name Description  
ICC1 Default IC card slot  
ICC2 Unavailable  
ICC3 Unavailable  
PSAM1 PSAM slot 1  
PSAM2 PSAM slot 2  
PSAM3 Unavailable  
RF Non-access card slot  
SWIPE Magnetic stripe card slot  
Return Value: None  
3.6.6 getRfCardType  
Get contactless card type  
public RfCardTypeEnum getRfCardType(CardSlotTypeEnum cardSlotType);  
Parameters:  
Parameter Description  
Confidential 66  
| ICC3 | Unavailable |  
| --- | --- |  
| PSAM1 | PSAM slot 1 |  
| PSAM2 | PSAM slot 2 |  
| PSAM3 | Unavailable |  
| RF | Non-access card slot |  
| SWIPE | Magnetic stripe card slot |  
| Parameter | Description |  
| --- | --- |  
| cardSlotType | CardSlotTypeEnum |  
| Enumeration Name | Description |  
| --- | --- |  
| ICC1 | Default IC card slot |  
| ICC2 | Unavailable |  
| ICC3 | Unavailable |  
| PSAM1 | PSAM slot 1 |  
| PSAM2 | PSAM slot 2 |  
| PSAM3 | Unavailable |  
| RF | Non-access card slot |  
| SWIPE | Magnetic stripe card slot |  
| Parameter | Description |  
| --- | --- |

## Página 67

SmartPos API Reference Manual: 09/08/21  
cardSlotType CardSlotTypeEnum  
CardSlotTypeEnum  
Enumeration Name Description  
ICC1 Default IC card slot  
ICC2 Unavailable  
ICC3 Unavailable  
PSAM1 PSAM slot 1  
PSAM2 PSAM slot 2  
PSAM3 Unavailable  
RF Non-access card slot  
SWIPE Magnetic stripe card slot  
RfCardTypeEnum  
Enumeration Name Description  
TYPE\_A\_CPU  
TYPE\_B\_CPU  
S50  
FELICA  
S70  
ULTRALIGHT  
MEMORY\_OTHER  
S50\_PRO  
S70\_PRO  
Return Value:  
Success return RfCardTypeEnum  
Fail null  
3.6.7 setETU  
reset ETU。  
public void setETU(CardSlotTypeEnum cardSlotType, int val);  
Confidential 67  
| cardSlotType | CardSlotTypeEnum |  
| --- | --- |  
| Enumeration Name | Description |  
| --- | --- |  
| ICC1 | Default IC card slot |  
| ICC2 | Unavailable |  
| ICC3 | Unavailable |  
| PSAM1 | PSAM slot 1 |  
| PSAM2 | PSAM slot 2 |  
| PSAM3 | Unavailable |  
| RF | Non-access card slot |  
| SWIPE | Magnetic stripe card slot |  
| Enumeration Name | Description |  
| --- | --- |  
| TYPE\_A\_CPU | |  
| TYPE\_B\_CPU | |  
| S50 | |  
| FELICA | |  
| S70 | |  
| ULTRALIGHT | |  
| MEMORY\_OTHER | |  
| S50\_PRO | |  
| S70\_PRO | |

## Página 68

SmartPos API Reference Manual: 09/08/21  
Parameters:  
Parameter Description  
cardSlotType CardSlotTypeEnum  
Value 0:372(standard card, default support  
val  
adaptive 4-fold)  
CardSlotTypeEnum  
Enumeration Name Description  
ICC1 Default IC card slot  
ICC2 Unavailable  
ICC3 Unavailable  
PSAM1 PSAM slot 1  
PSAM2 PSAM slot 2  
PSAM3 Unavailable  
RF Non-access card slot  
SWIPE Magnetic stripe card slot  
Return Value:  
None  
3.6.8 setSupportFelica  
set if support Felica Card .  
public void setSupportFelica(boolean var1);  
Return Value:  
None  
3.6.9 setFelicaSystemCode  
set Felica Card system code  
void setFelicaSystemCode(byte[] code);  
Return Value:  
Confidential 68  
| Parameter | Description |  
| --- | --- |  
| cardSlotType | CardSlotTypeEnum |  
| val | Value 0:372(standard card, default support adaptive 4-fold) |  
| Enumeration Name | Description |  
| --- | --- |  
| ICC1 | Default IC card slot |  
| ICC2 | Unavailable |  
| ICC3 | Unavailable |  
| PSAM1 | PSAM slot 1 |  
| PSAM2 | PSAM slot 2 |  
| PSAM3 | Unavailable |  
| RF | Non-access card slot |  
| SWIPE | Magnetic stripe card slot |

## Página 69

SmartPos API Reference Manual: 09/08/21  
None  
3.6.10 setFelicaRequestCode  
set Felica Request Code.  
void setFelicaRequestCode(byte code);  
Return Value:  
None  
3.7 CPU Cards  
The CPU card class is responsible for managing the CPU card.  
Get the object of the CPU card class:  
CPUCardHandler cpucard = deviceEngine getCPUCardHandler (CardSlotTypeEnum slotType ).;  
Parameters:  
Parameter Description  
slotType Card slot type  
CardSlotTypeEnum  
Enumeration Name Description  
ICC1 Default IC card slot  
ICC2 Unavailable  
ICC3 Unavailable  
PSAM1 PSAM slot 1  
PSAM2 PSAM slot 2  
PSAM3 Unavailable  
RF Non-access card slot  
This module operates using the basic flow chart:  
3.7.1 readUid  
Confidential 69  
| Parameter | Description |  
| --- | --- |  
| slotType | Card slot type |  
| Enumeration Name | Description |  
| --- | --- |  
| ICC1 | Default IC card slot |  
| ICC2 | Unavailable |  
| ICC3 | Unavailable |  
| PSAM1 | PSAM slot 1 |  
| PSAM2 | PSAM slot 2 |  
| PSAM3 | Unavailable |  
| RF | Non-access card slot |

## Página 70

SmartPos API Reference Manual: 09/08/21  
Read Uid of the card  
Public String readUid ();  
Parameters: None  
Return Value:  
Success Uid  
Note : if the card is Felica , the UID = IDm + PMm  
Failure Null  
3.7.2 powerOn  
Power-on reset, only for ICC1, PSAM1, PSAM2.  
Public boolean powerOn (byte [] atr);  
Parameters:  
Parameter Description  
atr Power returns atr, the first length byte  
hexadecimal representation, followed by the standard atr data  
Return Value:  
True success  
False failure  
3.7.3 active  
Activated, only for contactless card(RF).  
Public boolean active ();  
Parameters: None  
Return Value:  
True success  
False failure  
3.7.4 exchangeAPDUCmd  
Interactive APDU command.  
Confidential 70  
| Parameter | Description |  
| --- | --- |  
| atr | Power returns atr, the first length byte hexadecimal representation, followed by the standard atr data |

## Página 71

SmartPos API Reference Manual: 09/08/21  
Public int exchangeAPDUCmd (APDUEntity cmd);  
Parameters:  
Parameter Description  
cmd APDUEntity Command data  
APDUEntity  
Attributes Description  
byte p1 Instruction to attach a specific Parameter  
byte p2 Instruction to attach a specific Parameter  
int lc The number of bytes to transfer data  
int le Expect the maximum number of bytes to  
return  
byte ins Instruction code  
byte cla Command category  
byte swa Back swa  
byte swb Back swb  
int dataOutLen Returns the length of the data  
byte [] dataIn Sent data  
byte [] dataOut Return data  
int overtime Timeout in milliseconds; recommended  
value 1000  
Return Value:  
SdkResult.Success success  
SdkResult.Fail failure  
3.7.5 exchangeAPDUCmd  
Interactive APDU command.  
public byte[] exchangeAPDUCmd(byte[] cmd);  
Parameters:  
Parameter Description  
cmd Apdu command data  
Return Value:  
Success return response data  
Confidential 71  
| Parameter | Description |  
| --- | --- |  
| cmd | APDUEntity Command data |  
| Attributes | Description |  
| --- | --- |  
| byte p1 | Instruction to attach a specific Parameter |  
| byte p2 | Instruction to attach a specific Parameter |  
| int lc | The number of bytes to transfer data |  
| int le | Expect the maximum number of bytes to return |  
| byte ins | Instruction code |  
| byte cla | Command category |  
| byte swa | Back swa |  
| byte swb | Back swb |  
| int dataOutLen | Returns the length of the data |  
| byte [] dataIn | Sent data |  
| byte [] dataOut | Return data |  
| int overtime | Timeout in milliseconds; recommended value 1000 |  
| Parameter | Description |  
| --- | --- |  
| cmd | Apdu command data |

## Página 72

SmartPos API Reference Manual: 09/08/21  
Fail null  
3.7.6 powerOff  
Power down.  
Public void powerOff();  
Parameters: None  
Return Value: None  
3.7.7 remove  
remove contactless card  
public boolean remove();  
Return Value:  
SdkResult.Success success  
SdkResult.Fail failure  
3.8 EMV class (Emvhandler2)  
The EMV class is responsible for managing the EMV operation of the POS.  
Get the object of the EMV class:  
EmvHandler2 EmvHandler = deviceEngine getEmvHandler2 (String appId );  
Parameters:  
Parameter Description  
appId Application ID is mainly used to distinguish between aid and capk storage  
paths  
3.8.1 delAllAid  
Confidential 72  
| Parameter | Description |  
| --- | --- |  
| appId | Application ID is mainly used to distinguish between aid and capk storage paths |

## Página 73

SmartPos API Reference Manual: 09/08/21  
Remove all AIDs.  
Public void delAllAid ();  
Parameters: None  
Return Value: None  
3.8.2 delOneAid  
Delete an AID.  
Public boolean delOneAid (byte [] aid);  
Parameters:  
Parameter Description  
aid Enter aid  
Return Value:  
True success  
False failure  
3.8.3 delAllCapk  
Remove all CAPK.  
Public void delAllCapk ();  
Parameters: None  
Return Value: None  
3.8.4 delOneCapk  
Delete a CAPK.  
Public boolean delOneCapk (byte [] rid, int capkIdx);  
Parameters:  
Parameter Description  
rid Enter rid  
capkIdx capk Index  
Return Value:  
True success  
Confidential 73  
| Parameter | Description |  
| --- | --- |  
| aid | Enter aid |  
| Parameter | Description |  
| --- | --- |  
| rid | Enter rid |  
| capkIdx | capk Index |

## Página 74

SmartPos API Reference Manual: 09/08/21  
False failure  
3.8.5 setAidParaList  
Set the AID.  
public int setAidParaList(List aidParaTlvList);  
Parameters:  
Parameter Description  
aidParaTlvList Aid list  
AidEntity  
attribute Description  
String aid Application ID  
int asi Application selection indicator  
0- needn't match exactly(partial match up to the  
length);  
1- match exactly  
String tacDefault Terminal Action Code – Default  
String tacOnline Terminal Action Code – Online  
String tacDenial Terminal Action Code – Denial  
String appVerNum Application Version Number  
String ddol DDOL  
long threshold Threshold value for biased random selection  
int maxTargetPercent The maximum target percentage to be used for  
biased random selection  
int targetPercent The target percentage to be used for random  
selection  
int onlinePinCap Terminal online Pin capability  
long floorLimit Contact floor limit  
Confidential 74  
| Parameter | Description |  
| --- | --- |  
| aidParaTlvList | Aid list |  
| attribute | Description |  
| --- | --- |  
| String aid | Application ID |  
| int asi | Application selection indicator 0- needn't match exactly(partial match up to the length); 1- match exactly |  
| String tacDefault | Terminal Action Code – Default |  
| String tacOnline | Terminal Action Code – Online |  
| String tacDenial | Terminal Action Code – Denial |  
| String appVerNum | Application Version Number |  
| String ddol | DDOL |  
| long threshold | Threshold value for biased random selection |  
| int maxTargetPercent | The maximum target percentage to be used for biased random selection |  
| int targetPercent | The target percentage to be used for random selection |  
| int onlinePinCap | Terminal online Pin capability |  
| long floorLimit | Contact floor limit |

## Página 75

SmartPos API Reference Manual: 09/08/21  
long transLimit Electronic cash limit(union pay used in china  
market)  
long contactlessCvmLimit Contactless cvm limit  
long contactlessTransLimit Contactless transaction limit  
long contactlessFloorLimit Contactless floor limit  
String transType Transaction type, EMV tag 9c, “00”-sale, “20”-  
refund..  
Default value is “FF”,it means adapt to all  
transaction type  
AidEntryModeEnum aidEntryModeEnum AID\_ENTRY\_CONTACT\_CONTACTLESS:  
default value, means this aid can used for both  
contact and contactless  
AID\_ENTRY\_CONTACT:  
This aid is only used for cotact  
AID\_ENTRY\_CONTACTLESS:  
This aid is only used for contactless  
So, the same aid can config 2 aid with  
aidEntryModeEnum different, one is only for  
contact, and one is only for contactless  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.8.6 setAidParaList  
Set the AID.  
Public int setAidParaList (List aidParaTlvList);  
Confidential 75  
| long transLimit | Electronic cash limit(union pay used in china market) |  
| --- | --- |  
| long contactlessCvmLimit | Contactless cvm limit |  
| long contactlessTransLimit | Contactless transaction limit |  
| long contactlessFloorLimit | Contactless floor limit |  
| String transType | Transaction type, EMV tag 9c, “00”-sale, “20”- refund.. Default value is “FF”,it means adapt to all transaction type |  
| AidEntryModeEnum aidEntryModeEnum | AID\_ENTRY\_CONTACT\_CONTACTLESS: default value, means this aid can used for both contact and contactless AID\_ENTRY\_CONTACT: This aid is only used for cotact AID\_ENTRY\_CONTACTLESS: This aid is only used for contactless So, the same aid can config 2 aid with aidEntryModeEnum different, one is only for contact, and one is only for contactless |

## Página 76

SmartPos API Reference Manual: 09/08/21  
Parameters:  
Parameter Description  
aidParaTlvList Enter the number of aid data list, such as:  
aidParaTlvList.add(ByteUtils.hexString2ByteArray("9F0607A0000000043060DF010  
1009F08020002DF1105FC5058A000DF1205F85058F800DF130504000000009F1B0  
400000000DF150400000000DF160199DF170199DF14039F3704DF180101DF20060  
00999999999"));  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.8.7 setAidParaList  
Set the AID.  
public int setAidParaList(List aidParaTlvList);  
Parameters:  
Parameter Description  
aidParaTlvList Enter the number of aid data list, such as:  
aidParaTlvList.add("9F0607A0000000043060DF01  
01009F08020002DF1105FC5058A000DF1205F850  
58F800DF130504000000009F1B0400000000DF15  
0400000000DF160199DF170199DF14039F3704DF  
180101DF2006000999999999");  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.8.8 setCAPKList  
Confidential 76  
| Parameter | Description |  
| --- | --- |  
| aidParaTlvList | Enter the number of aid data list, such as: aidParaTlvList.add(ByteUtils.hexString2ByteArray("9F0607A0000000043060DF010 1009F08020002DF1105FC5058A000DF1205F85058F800DF130504000000009F1B0 400000000DF150400000000DF160199DF170199DF14039F3704DF180101DF20060 00999999999")); |  
| Parameter | Description |  
| --- | --- |  
| aidParaTlvList Enter t | he number of aid data list, such as: aidParaTlvList.add("9F0607A0000000043060DF01 01009F08020002DF1105FC5058A000DF1205F850 58F800DF130504000000009F1B0400000000DF15 0400000000DF160199DF170199DF14039F3704DF 180101DF2006000999999999"); |

## Página 77

SmartPos API Reference Manual: 09/08/21  
Set CAPK.  
public int setCAPKList(List capkTlvList);  
参数项：  
Parameter Description  
capkTlvList Capk list  
CapkEntity  
attribute Description  
String rid Registered Application Identifier  
int capkIdx Unique CA public key index number  
int hashInd Cryptographic algorithm ID used to generate the  
CAPK  
String modulus CA Public Key modulus  
String exponent CA Public Key exponent  
String checkSum CA Public Key checkSum  
String expireDate CA Public Key expireDate(YYYYMMDD)  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.8.9 setCAPKList  
Set CAPK.  
Public int setCAPKList (List capkTlvList);  
Parameters:  
Parameter Description  
capkTlvList Enter multiple capk data list, such as:  
capkTlvList.add (ByteUtils.hexString2ByteArray  
("9F0605A0000000659F220109DF05083230303931323331DF060101DF070101DF02  
8180B72A8FEF5B27F2B550398FDCC256F714BAD497FF56094B7408328CB626AA6F0  
E6A9DF8388EB9887BC930170BCC1213E90FC070D52C8DCD0FF9E10FAD36801FE93F  
C998A721705091F18BC7C98241CADC15A2B9DA7FB963142C0AB640D5D0135E77EB  
AE95AF1B4FEFADCF9C012366BDDA0455C1564A68810D7127676D493890BDDF0401  
03DF03144410C6D51C2F83ADFD92528FA6E38A32DF048D0A"));  
Confidential 77  
| Parameter | Description |  
| --- | --- |  
| capkTlvList | Capk list |  
| attribute | Description |  
| --- | --- |  
| String rid | Registered Application Identifier |  
| int capkIdx | Unique CA public key index number |  
| int hashInd | Cryptographic algorithm ID used to generate the CAPK |  
| String modulus | CA Public Key modulus |  
| String exponent | CA Public Key exponent |  
| String checkSum | CA Public Key checkSum |  
| String expireDate | CA Public Key expireDate(YYYYMMDD) |  
| Parameter | Description |  
| --- | --- |  
| capkTlvList | Enter multiple capk data list, such as: capkTlvList.add (ByteUtils.hexString2ByteArray ("9F0605A0000000659F220109DF05083230303931323331DF060101DF070101DF02 8180B72A8FEF5B27F2B550398FDCC256F714BAD497FF56094B7408328CB626AA6F0 E6A9DF8388EB9887BC930170BCC1213E90FC070D52C8DCD0FF9E10FAD36801FE93F C998A721705091F18BC7C98241CADC15A2B9DA7FB963142C0AB640D5D0135E77EB AE95AF1B4FEFADCF9C012366BDDA0455C1564A68810D7127676D493890BDDF0401 03DF03144410C6D51C2F83ADFD92528FA6E38A32DF048D0A")); |

## Página 78

SmartPos API Reference Manual: 09/08/21  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.8.10 setCAPKList  
Set CAPK.  
public int setCAPKList(List capkTlvList);  
Parameters:  
Parameter Description  
capkTlvList Enter multiple capk data list, such as:  
capkTlvList.add("9F0605A0000000659F220109DF050  
83230303931323331DF060101DF070101DF028180B  
72A8FEF5B27F2B550398FDCC256F714BAD497FF560  
94B7408328CB626AA6F0E6A9DF8388EB9887BC9301  
70BCC1213E90FC070D52C8DCD0FF9E10FAD36801FE  
93FC998A721705091F18BC7C98241CADC15A2B9DA  
7FB963142C0AB640D5D0135E77EBAE95AF1B4FEFA  
DCF9C012366BDDA0455C1564A68810D7127676D49  
3890BDDF040103DF03144410C6D51C2F83ADFD925  
28FA6E38A32DF048D0A");  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.8.11 getAidListNum  
get aid list number  
public int getAidListNum();  
Return Value:  
Confidential 78  
| Parameter | Description |  
| --- | --- |  
| capkTlvList | Enter multiple capk data list, such as: capkTlvList.add("9F0605A0000000659F220109DF050 83230303931323331DF060101DF070101DF028180B 72A8FEF5B27F2B550398FDCC256F714BAD497FF560 94B7408328CB626AA6F0E6A9DF8388EB9887BC9301 70BCC1213E90FC070D52C8DCD0FF9E10FAD36801FE 93FC998A721705091F18BC7C98241CADC15A2B9DA 7FB963142C0AB640D5D0135E77EBAE95AF1B4FEFA DCF9C012366BDDA0455C1564A68810D7127676D49 3890BDDF040103DF03144410C6D51C2F83ADFD925 28FA6E38A32DF048D0A"); |

## Página 79

SmartPos API Reference Manual: 09/08/21  
Number of aid list  
3.8.12 getAidList  
get aid list  
public List getAidList();  
AidEntity  
Attributes Description  
String aid Application ID  
int asi Application selection indicator  
0- needn't match exactly(partial match up to the  
length);  
1- match exactly  
String tacDefault Terminal Action Code – Default  
String tacOnline Terminal Action Code – Online  
String tacDenial Terminal Action Code – Denial  
String appVerNum Application Version Number  
String DDOL DDOL  
long threshold Threshold value for biased random selection  
int maxTargetPercent The maximum target percentage to be used for  
biased random selection  
int targetPercent The target percentage to be used for random  
selection  
int onlinePinCap Terminal online Pin capability  
long floorLimit  
long transLimit  
long contactlessCvmLimit  
long contactlessTransLimit  
long contactlessFloorLimit  
Return Value:  
Success return aid list  
Fail return null  
3.8.13 getCapkListNum  
Confidential 79  
| Attributes | Description |  
| --- | --- |  
| String aid | Application ID |  
| int asi | Application selection indicator 0- needn't match exactly(partial match up to the length); 1- match exactly |  
| String tacDefault | Terminal Action Code – Default |  
| String tacOnline | Terminal Action Code – Online |  
| String tacDenial | Terminal Action Code – Denial |  
| String appVerNum | Application Version Number |  
| String DDOL | DDOL |  
| long threshold | Threshold value for biased random selection |  
| int maxTargetPercent | The maximum target percentage to be used for biased random selection |  
| int targetPercent | The target percentage to be used for random selection |  
| int onlinePinCap | Terminal online Pin capability |  
| long floorLimit | |  
| long transLimit | |  
| long contactlessCvmLimit | |  
| long contactlessTransLimit | |  
| long contactlessFloorLimit | |

## Página 80

SmartPos API Reference Manual: 09/08/21  
get capk list number  
public int getCapkListNum();  
Return Value:  
Number of capk list  
3.8.14 getCapkList  
get capk list  
public List getCapkList();  
CapkEntity  
Attributes Description  
String rid Registered Application Identifier  
int capkIdx Unique CA public key index number  
int hashInd Cryptographic algorithm ID used to generate the  
CAPK  
String modulus CA Public Key modulus  
String exponent CA Public Key exponent  
String checkSum CA Public Key checkSum  
String expireDate CA Public Key expireDate(MMYY)  
Return Value:  
Success return capk list  
Fail return null  
3.8.15 emvDebugLog  
enable EMV log for checking emv issues, default false  
public void emvDebugLog(boolean isEnable);  
Parameters:  
Parameter Description  
isEnable  
True ，false  
Confidential 80  
| Attributes | Description |  
| --- | --- |  
| String rid | Registered Application Identifier |  
| int capkIdx | Unique CA public key index number |  
| int hashInd | Cryptographic algorithm ID used to generate the CAPK |  
| String modulus | CA Public Key modulus |  
| String exponent | CA Public Key exponent |  
| String checkSum | CA Public Key checkSum |  
| String expireDate | CA Public Key expireDate(MMYY) |  
| Parameter | Description |  
| --- | --- |  
| isEnable | True ，false |

## Página 81

SmartPos API Reference Manual: 09/08/21  
Return Value: None  
3.8.16 setDynamicReaderLimitListForPaywave  
Set DRL for paywave  
public int setDynamicReaderLimitListForPaywave(List drlEntityList)  
Parameters:  
Parameter Description  
drlEntityList  
DRL list  
DynamicReaderLimitEntity  
attribute Description  
byte[] appProgID  
boolean statusCheck  
boolean authOfZeroCheck  
byte authOfZeroCheckOption;  
boolean readerContactlessTransLimitCheck;  
boolean readerCVMReqLimitCheck;  
boolean readerContactlessFloorLimitCheck;  
private boolean drlSupport;  
byte[] readerContactlessTransLimit;  
byte[] readerCVMReqLimit;  
byte[] readerContactlessFloorLimit;  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.8.17 setDynamicReaderLimitListForExpressPay  
Confidential 81  
| Parameter | Description |  
| --- | --- |  
| drlEntityList | DRL list |  
| attribute | Description |  
| --- | --- |  
| byte[] appProgID | |  
| boolean statusCheck | |  
| boolean authOfZeroCheck | |  
| byte authOfZeroCheckOption; | |  
| boolean readerContactlessTransLimitCheck; | |  
| boolean readerCVMReqLimitCheck; | |  
| boolean readerContactlessFloorLimitCheck; | |  
| private boolean drlSupport; | |  
| byte[] readerContactlessTransLimit; | |  
| byte[] readerCVMReqLimit; | |  
| byte[] readerContactlessFloorLimit; | |

## Página 82

SmartPos API Reference Manual: 09/08/21  
Set DRL for Amex Expresspay  
public int setDynamicReaderLimitListForExpressPay (List drlEntityList)  
Parameters:  
Parameter Description  
drlEntityList  
DRL list  
DynamicReaderLimitEntity  
attribute Description  
byte[] appProgID Application Prog ID  
boolean statusCheck statusCheck  
boolean authOfZeroCheck  
byte authOfZeroCheckOption;  
boolean readerContactlessTransLimitCheck;  
boolean readerCVMReqLimitCheck;  
boolean readerContactlessFloorLimitCheck;  
private boolean drlSupport;  
byte[] readerContactlessTransLimit;  
byte[] readerCVMReqLimit;  
byte[] readerContactlessFloorLimit;  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.8.18 getTlv  
Get tag.  
Public byte [] getTlv (byte [] tag, EmvDataSourceEnum pathId);  
Parameters:  
Parameter Description  
Confidential 82  
| Parameter | Description |  
| --- | --- |  
| drlEntityList | DRL list |  
| attribute | Description |  
| --- | --- |  
| byte[] appProgID | Application Prog ID |  
| boolean statusCheck | statusCheck |  
| boolean authOfZeroCheck | |  
| byte authOfZeroCheckOption; | |  
| boolean readerContactlessTransLimitCheck; | |  
| boolean readerCVMReqLimitCheck; | |  
| boolean readerContactlessFloorLimitCheck; | |  
| private boolean drlSupport; | |  
| byte[] readerContactlessTransLimit; | |  
| byte[] readerCVMReqLimit; | |  
| byte[] readerContactlessFloorLimit; | |  
| Parameter | Description |  
| --- | --- |

## Página 83

SmartPos API Reference Manual: 09/08/21  
tag tag value  
pathId tag source  
EmvDataSourceEnum  
Enumeration Name Description  
FROM\_KERNEL Data sources kernel  
FORM\_CARD Data sources cards  
Return Value:  
Tlv successful Return Value  
Else return null  
3.8.19 getTlvByTags  
public String getTlvByTags(String[] tags);  
Parameters:  
Parameter Description  
tags Tag such as: String[] TAGS = {"9f26", "9f27",  
"9f10", "9f37", "9f36", "95", "9a", "9c", "9f02",  
"5f2a", "82", "9f1a", "9f03","9f33", "9f34", "9f35",  
"9f1e", "9f09", "84", "9f41"}  
Return Value:  
Tlv successful Return string Value  
Else return null  
3.8.20 setTlv  
Settings tag for EMV processing  
public int setTlv (byte [] tag, byte [] value);  
Parameters:  
Parameter Description  
Confidential 83  
| tag | tag value |  
| --- | --- |  
| pathId | tag source |  
| Enumeration Name | Description |  
| --- | --- |  
| FROM\_KERNEL | Data sources kernel |  
| FORM\_CARD | Data sources cards |  
| Parameter | Description |  
| --- | --- |  
| tags | Tag such as: String[] TAGS = {"9f26", "9f27", "9f10", "9f37", "9f36", "95", "9a", "9c", "9f02", "5f2a", "82", "9f1a", "9f03","9f33", "9f34", "9f35", "9f1e", "9f09", "84", "9f41"} |  
| Parameter | Description |  
| --- | --- |

## Página 84

SmartPos API Reference Manual: 09/08/21  
tag tag value  
value data  
Return Value:  
SdkResult.Success success  
SdkResult.Fail failure  
SdkResult.Param\_In\_Invalid Parameter error  
3.8.21 initTermConfig  
Allows the user to set the terminal personalization attribute, initialize the EMV kernel, and use the EMV  
kernel default attribute if the user does not call it. (Not recommended, please use method setTlv instead  
of this method)  
Public int initTermConfig (byte [] cfgTlv);  
Parameters:  
Parameter Description  
cfgTlv Standard tlv data stream  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.8.22 emvProcess  
Start emv process  
Public int emvProcess (EmvTransConfigurationEntity transData, OnEmvProcessListener2 listener);  
Parameters:  
Parameter Description  
transData EMV transactions Entity Info  
listener EMV flow monitor interfaces  
EmvTransDataEntity  
Attributes Description  
Confidential 84  
| tag | tag value |  
| --- | --- |  
| value | data |  
| Parameter | Description |  
| --- | --- |  
| cfgTlv | Standard tlv data stream |  
| Parameter | Description |  
| --- | --- |  
| transData | EMV transactions Entity Info |  
| listener | EMV flow monitor interfaces |  
| Attributes | Description |  
| --- | --- |

## Página 85

SmartPos API Reference Manual: 09/08/21  
String traceNo trace number, length 8  
String transAmt Amount, length 12, for example “000000010000” = 100.00  
String cashbackAmt Cash back amount, length 12  
String transDate Transaction date MMDD, length 4  
String transTime Transaction date HHMMSS, length 6  
byte[] merName merchant name  
String merId merchant ID, length 15  
String termId terminal ID, length 8  
byte emvTransType EMV Transaction Type, sale-0x00, refund-0x20…  
String countryCode Country code , emv tage 9f1a  
String currencyCode Currency code, emv tag 5f2a  
EmvEntryModeEnum entryModeEnum Entry mode: contact or contactless  
EmvProcessFlowEnum Standard flow(full flow)  
processFlowEnum Read app data flow  
boolean isContactForceOnline True: Contact transaction force online  
False: standard process  
MasterCardTransDataEntity entity Master card parameter  
VisaTransDataEntity entity Visa parameter  
AmexTransDataEntity entity Amex parameters  
UnionPayTransDataEntity entity UnionPay parameter  
EmvProcessFlowEnum  
Enumeration Name Description  
EMV\_PROCESS\_FLOW\_STANDARD Standard emv flow  
EMV\_PROCESS\_FLOW\_READ\_APPDATA Read application data, card number ..etc(it is  
suitable for contact and contactless)  
EmvEntryModeEnum  
Enumeration Name Description  
EMV\_ENTRY\_MODE\_CONTACT Contact  
EMV\_ENTRY\_MODE\_CONTACTLESS Contactless  
MasterCardTransDataEntity  
Confidential 85  
| String traceNo | trace number, length 8 |  
| --- | --- |  
| String transAmt | Amount, length 12, for example “000000010000” = 100.00 |  
| String cashbackAmt | Cash back amount, length 12 |  
| String transDate | Transaction date MMDD, length 4 |  
| String transTime | Transaction date HHMMSS, length 6 |  
| byte[] merName | merchant name |  
| String merId | merchant ID, length 15 |  
| String termId | terminal ID, length 8 |  
| byte emvTransType | EMV Transaction Type, sale-0x00, refund-0x20… |  
| String countryCode | Country code , emv tage 9f1a |  
| String currencyCode | Currency code, emv tag 5f2a |  
| EmvEntryModeEnum entryModeEnum | Entry mode: contact or contactless |  
| EmvProcessFlowEnum processFlowEnum | Standard flow(full flow) Read app data flow |  
| boolean isContactForceOnline | True: Contact transaction force online False: standard process |  
| MasterCardTransDataEntity entity | Master card parameter |  
| VisaTransDataEntity entity | Visa parameter |  
| AmexTransDataEntity entity | Amex parameters |  
| UnionPayTransDataEntity entity | UnionPay parameter |  
| Enumeration Name | Description |  
| --- | --- |  
| EMV\_PROCESS\_FLOW\_STANDARD | Standard emv flow |  
| EMV\_PROCESS\_FLOW\_READ\_APPDATA | Read application data, card number ..etc(it is suitable for contact and contactless) |  
| Enumeration Name | Description |  
| --- | --- |  
| EMV\_ENTRY\_MODE\_CONTACT | Contact |  
| EMV\_ENTRY\_MODE\_CONTACTLESS | Contactless |

## Página 86

SmartPos API Reference Manual: 09/08/21  
Enumeration Name Description  
Boolean isSupportContactQps True: support conatact QPS  
False: do not support  
String contactNoCvmLimit Contact QPS limit, 12 bytes. If transaction  
amount < contactNoCvmLimit, for master credit  
card, No cvm replace signature.  
VisaTransDataEntity  
Enumeration Name Description  
Boolean isSupportContactQps True : support conatact QPS  
False: do not support  
String contactNoCvmLimit Contact QPS limit, 12 bytes. If transaction  
amount < contactNoCvmLimit, for master credit  
card, No cvm replace signature.  
AmexTransDataEntity  
Enumeration Name Description  
Boolean isExpressPaySeePhoneTapCardAgain express pay see phone test cases, the second tap  
should set the value true  
UnionPayTransDataEntity  
Enumeration Name Description  
Boolean isForceOnline Force online  
Boolean isSupportCDCVM Support CDCVM, default value is true  
Boolean isQpbocForGlobal if use China market, please set false, others  
please set true. Default value is true  
Boolean isSupportContactlessQps Support QPS  
String contactlessQpsLimit QPS limit  
Return Value:  
SdkResult.Success success execution listener callback  
SdkResult.Param\_In\_Invalid illegal Parameter  
Confidential 86  
| Enumeration Name | Description |  
| --- | --- |  
| Boolean isSupportContactQps | True: support conatact QPS False: do not support |  
| String contactNoCvmLimit | Contact QPS limit, 12 bytes. If transaction amount < contactNoCvmLimit, for master credit card, No cvm replace signature. |  
| Enumeration Name | Description |  
| --- | --- |  
| Boolean isSupportContactQps | True : support conatact QPS False: do not support |  
| String contactNoCvmLimit | Contact QPS limit, 12 bytes. If transaction amount < contactNoCvmLimit, for master credit card, No cvm replace signature. |  
| Enumeration Name | Description |  
| --- | --- |  
| Boolean isExpressPaySeePhoneTapCardAgain | express pay see phone test cases, the second tap should set the value true |  
| Enumeration Name | Description |  
| --- | --- |  
| Boolean isForceOnline | Force online |  
| Boolean isSupportCDCVM | Support CDCVM, default value is true |  
| Boolean isQpbocForGlobal | if use China market, please set false, others please set true. Default value is true |  
| Boolean isSupportContactlessQps | Support QPS |  
| String contactlessQpsLimit | QPS limit |

## Página 87

SmartPos API Reference Manual: 09/08/21  
3.8.23 onSetSelAppResponse  
After executing the OnEMVProcessListener2. OnSelApp method, call it to notify the EMV kernel to  
continue the process.  
Public void onSetSelAppResponse (int selResult);  
Parameters:  
Parameter Description  
selResult After selecting the AID index number, the index starts at 1; the  
method is performed by onSelApp after obtained.  
Return Value: None  
3.8.24 onSetTransInitBeforeGPOResponse  
After executing the OnEMVProcessListener2. onTransInitBeforeGPO method, call it to notify the EMV  
kernel to continue the process.  
Public void onSetTransInitBeforeGPOResponse (boolean isSuccess);  
Parameters:  
Parameter Description  
isSuccess Default value: true.  
The result of final select application.  
Return Value: None  
3.8.25 onSetConfirmCardNoResponse  
After executing the OnEmvProcessListener2. OnConfirmCardNo method, call it to notify the EMV kernel  
to continue the process.  
Public void onSetConfirmCardNoResponse (boolean isConfirm);  
Parameters:  
Parameter Description  
isConfirm Are you sure, true: yes, false: no  
Return Value: None  
Confidential 87  
| Parameter | Description |  
| --- | --- |  
| selResult | After selecting the AID index number, the index starts at 1; the method is performed by onSelApp after obtained. |  
| Parameter | Description |  
| --- | --- |  
| isSuccess | Default value: true. The result of final select application. |  
| Parameter | Description |  
| --- | --- |  
| isConfirm | Are you sure, true: yes, false: no |

## Página 88

SmartPos API Reference Manual: 09/08/21  
3.8.26 onSetPinInputResponse  
After executing the OnEMVProcessListener2. OnCardHolderInputPin method, call it to notify the EMV  
kernel to continue the process.  
Public void onSetPinInputResponse (boolean isConfirm, boolean isBypass);  
Parameters:  
Parameter Description  
isConfirm Whether the Enter key is pressed  
isBypass If no password is entered, press the Enter key  
Return Value: None  
3.8.27 onSetContactlessTapCardResponse  
After executing OnEmvProcessListener2. onContactlessTapCardAgain method，call it to notify the EMV  
kernel to continue the process.  
public void onSetContactlessTapCardResponse (boolean isSuccess);  
Parameters:  
Parameter Description  
isSuccess isSuccess，true:yes，false:no  
Return Value: None  
3.8.28 onSetOnlineProcResponse  
After executing the OnEmvProcessListener. OnOnlineProc method, call it to notify the EMV kernel to  
take the secondary authorization.  
public void onSetOnlineProcResponse (int retCode, EmvOnlineResultEntity result);  
Parameters:  
Parameter Description  
retCode SdkResult.Success: connect to the host successfully.  
SdkResult.Fail: unable connect to the host.  
result EmvOnlineResultEntity, EMV online results  
EmvOnlineResultEntity  
Confidential 88  
| Parameter | Description |  
| --- | --- |  
| isConfirm | Whether the Enter key is pressed |  
| isBypass | If no password is entered, press the Enter key |  
| Parameter | Description |  
| --- | --- |  
| isSuccess | isSuccess，true:yes，false:no |  
| Parameter | Description |  
| --- | --- |  
| retCode | SdkResult.Success: connect to the host successfully. SdkResult.Fail: unable connect to the host. |  
| result | EmvOnlineResultEntity, EMV online results |

## Página 89

SmartPos API Reference Manual: 09/08/21  
Attributes Description  
String rejCode Host respond with transaction response codes  
String authCode Host respond with Transaction Authorization  
Code  
Byte [] recvField55 Host respond 55 field data  
Return Value: None  
3.8.29 onSetPromptResponse  
After executing OnEmvProcessListener. onPrompt method，call it to notify the EMV kernel to continue  
the process.  
public void onSetPromptResponse (boolean isSuccess);  
Parameters:  
Parameter Description  
isSuccess isSuccess，true:yes，false:no  
Return Value: None  
3.8.30 onSetRemoveCardResponse  
After executing OnEmvProcessListener. onRemoveCard method，call it to notify the EMV kernel to  
continue the process.  
public void onSetRemoveCardResponse (boolean isSuccess);  
Parameters:  
Parameter Description  
isSuccess isSuccess，true:yes，false:no  
Return Value: None  
3.8.31 EMVProcessCancel  
Confidential 89  
| Attributes | Description |  
| --- | --- |  
| String rejCode | Host respond with transaction response codes |  
| String authCode | Host respond with Transaction Authorization Code |  
| Byte [] recvField55 | Host respond 55 field data |  
| Parameter | Description |  
| --- | --- |  
| isSuccess | isSuccess，true:yes，false:no |  
| Parameter | Description |  
| --- | --- |  
| isSuccess | isSuccess，true:yes，false:no |

## Página 90

SmartPos API Reference Manual: 09/08/21  
Cancel EMV process.  
public void emvProcessCancel ();  
Parameters: None  
Return Value: None  
3.8.32 EMVProcessAbort  
Force quite EMV process.  
public void emvProcessAbort ();  
Parameters: None  
Return Value: None  
3.8.33 getEmvContactlessMode  
get EMV contactless flow mode, EMV mode or MSD mode, should be called in method  
onOnlineProc or onFinish method  
public EmvModeEnum getEmvContactlessMode();  
Return Value:  
EmvModeEnum  
Enumeration Name Description  
EMV EMV mode  
MSD MSD mode  
UNDEF UNDEF mode  
LEGACY LEGACY mode  
3.8.34 contactlessSetAidFirstSelect  
set which AID first select for contactless transaction. It should be called before emvProcess.  
Confidential 90  
| Enumeration Name | Description |  
| --- | --- |  
| EMV | EMV mode |  
| MSD | MSD mode |  
| UNDEF | UNDEF mode |  
| LEGACY | LEGACY mode |

## Página 91

SmartPos API Reference Manual: 09/08/21  
public int contactlessSetAidFirstSelect (byte aidLen, byte[] aid);  
parameter：  
Attributes Description  
aidLen AID length  
aid AID  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.8.35 setPureKernelCapab  
set pure kernel capability. It should be called in method "onTransInitBeforeGPO"  
int setPureKernelCapab(byte[] capab);  
parameter：  
Attributes Description  
Capability, 5 bytes  
capab  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.8.36 setJcbContactlessTIP  
set JCB contactless TIP. It should be called in method "onTransInitBeforeGPO"  
void setJcbContactlessTIP(byte[] terminalInterchangeProfile);  
parameter：  
Confidential 91  
| Attributes | Description |  
| --- | --- |  
| aidLen | AID length |  
| aid | AID |  
| Attributes | Description |  
| --- | --- |  
| capab | Capability, 5 bytes |

## Página 92

SmartPos API Reference Manual: 09/08/21  
Attributes Description  
TIP  
terminalInterchangeProfile  
Return Value:  
None  
3.8.37 setRupayTransType  
set Rupay contactless transaction type. It should be called in method "onTransInitBeforeGPO"  
void setRupayTransType (RupayTransType transType);  
parameter：  
Attributes Description  
transType Transaction Type  
RupayTransType  
Attributes Description  
RUPAY\_TRANSTYPE\_GOODS GOODS, 0x00  
RUPAY\_TRANSTYPE\_CASH CASH, 0x01  
RUPAY\_TRANSTYPE\_CASHBACK CASHBACK, 0x19  
RUPAY\_TRANSTYPE\_MONEYADD MONEYADD, 0x28  
RUPAY\_TRANSTYPE\_BALANCEENQUIRY BALANCEENQUIRY,0x31  
RUPAY\_TRANSTYPE\_VOID VOID,0x34  
RUPAY\_TRANSTYPE\_SERVICECREATION SERVICE CREATION,0x83  
RUPAY\_TRANSTYPE\_OTHER DEFAULT, 0xff  
Return Value:  
None  
Confidential 92  
| Attributes | Description |  
| --- | --- |  
| terminalInterchangeProfile | TIP |  
| Attributes | Description |  
| --- | --- |  
| transType | Transaction Type |  
| Attributes | Description |  
| --- | --- |  
| RUPAY\_TRANSTYPE\_GOODS | GOODS, 0x00 |  
| RUPAY\_TRANSTYPE\_CASH | CASH, 0x01 |  
| RUPAY\_TRANSTYPE\_CASHBACK | CASHBACK, 0x19 |  
| RUPAY\_TRANSTYPE\_MONEYADD | MONEYADD, 0x28 |  
| RUPAY\_TRANSTYPE\_BALANCEENQUIRY | BALANCEENQUIRY,0x31 |  
| RUPAY\_TRANSTYPE\_VOID | VOID,0x34 |  
| RUPAY\_TRANSTYPE\_SERVICECREATION | SERVICE CREATION,0x83 |  
| RUPAY\_TRANSTYPE\_OTHER | DEFAULT, 0xff |

## Página 93

SmartPos API Reference Manual: 09/08/21  
3.8.38 getJcbContactlessTIP  
get JCB contactless TIP.  
byte[] getJcbContactlessTIP();  
Return Value:  
JCB contactless TIP  
3.8.39 getSignNeed  
get cvm result if need signature  
public boolean getSignNeed();  
Return Value:  
ture need signature  
false not need signature  
3.8.40 getEmvCvmResult  
get EMV CVM result  
EmvCvmResultEnum getEmvCvmResult();  
Return Value:  
EmvCvmResultEnum  
Enumeration Name Description  
EMV\_CVMR\_NA CVM result is not specified, or the result is null  
EMV\_CVMR\_NOCVM No cvm required  
EMV\_CVMR\_SIGNATURE Signature  
Confidential 93  
| Enumeration Name | Description |  
| --- | --- |  
| EMV\_CVMR\_NA | CVM result is not specified, or the result is null |  
| EMV\_CVMR\_NOCVM | No cvm required |  
| EMV\_CVMR\_SIGNATURE | Signature |

## Página 94

SmartPos API Reference Manual: 09/08/21  
EMV\_CVMR\_ONLINEPIN Online pin  
EMV\_CVMR\_CONFVERIFIED ID verify (not used)  
EMV\_CVMR\_CDCVM CDCVM  
EMV\_CVMR\_OFFLINEPIN\_PLAINTEXT Offline plaintext pin  
EMV\_CVMR\_OFFLINEPIN\_ENCIPHER Offline encipher pin  
EMV\_CVMR\_OFFLINEPIN\_PLAINTEXT\_SIGNATURE Offline plaintext pin & signature  
EMV\_CVMR\_OFFLINEPIN\_ENCIPHER\_SIGNATURE Offline encipher pin & signature  
EMV\_CVMR\_SKIP\_CVM Skip cvm, used for MIR  
3.8.41 getEmvCardDataInfo  
get EMV card data, such as pan, track2 data  
CardInfoEntity getEmvCardDataInfo();  
Return Value:  
CardInfoEntity  
Attributes Description  
String cardNo Card number  
CardSlotTypeEnum cardExistslot CardSlotType  
RfCardTypeEnum rfCardType RfCardTyp  
String tk1 track 1  
String tk2 tracks 2  
String tk3 tracks 3  
String expiredDate Card is valid  
String serviceCode Service Code  
boolean isTk1Valid A track LRC is correct  
boolean isTk2Valid Two tracks LRC is correct  
boolean isTk3Valid Three tracks LRC is correct  
boolean isICC If mag card has chip flag  
String csn Card serial number, only returnd in  
OnEmvProcessListener.onConfirmCardNo  
CardSlotTypeEnum  
Confidential 94  
| EMV\_CVMR\_ONLINEPIN | Online pin |  
| --- | --- |  
| EMV\_CVMR\_CONFVERIFIED | ID verify (not used) |  
| EMV\_CVMR\_CDCVM | CDCVM |  
| EMV\_CVMR\_OFFLINEPIN\_PLAINTEXT | Offline plaintext pin |  
| EMV\_CVMR\_OFFLINEPIN\_ENCIPHER | Offline encipher pin |  
| EMV\_CVMR\_OFFLINEPIN\_PLAINTEXT\_SIGNATURE | Offline plaintext pin & signature |  
| EMV\_CVMR\_OFFLINEPIN\_ENCIPHER\_SIGNATURE | Offline encipher pin & signature |  
| EMV\_CVMR\_SKIP\_CVM | Skip cvm, used for MIR |  
| Attributes | Description |  
| --- | --- |  
| String cardNo | Card number |  
| CardSlotTypeEnum cardExistslot | CardSlotType |  
| RfCardTypeEnum rfCardType | RfCardTyp |  
| String tk1 | track 1 |  
| String tk2 | tracks 2 |  
| String tk3 | tracks 3 |  
| String expiredDate | Card is valid |  
| String serviceCode | Service Code |  
| boolean isTk1Valid | A track LRC is correct |  
| boolean isTk2Valid | Two tracks LRC is correct |  
| boolean isTk3Valid | Three tracks LRC is correct |  
| boolean isICC | If mag card has chip flag |  
| String csn | Card serial number, only returnd in OnEmvProcessListener.onConfirmCardNo |

## Página 95

SmartPos API Reference Manual: 09/08/21  
Enumeration Name Description  
ICC1 The I C slot 1  
ICC2 The I C slot 2  
ICC3 The I C slot 3  
PSAM1 PSAM slot 1  
PSAM2 PSAM slot 1  
PSAM3 PSAM slot 1  
RF Contactless card slot  
SWIPE Magnetic stripe card slot  
RfCardTypeEnum  
Enumeration Name Description  
TYPE\_A\_CPU  
TYPE\_B\_CPU  
S50  
FELICA  
S70  
ULTRALIGHT  
MEMORY\_OTHER  
S50\_PRO  
S70\_PRO  
3.8.42 getEmvContactlessKernelId  
get EMV contactless kernel ID  
byte[] getEmvContactlessKernelId();  
Return Value:  
Success return Kernel ID, otherwise return null.  
3.8.43 contactlessAppendAidIntoKernel  
For special contactless kernel, application can pass the specify AID to expect kernel process.  
Confidential 95  
| Enumeration Name | Description |  
| --- | --- |  
| ICC1 | The I C slot 1 |  
| ICC2 | The I C slot 2 |  
| ICC3 | The I C slot 3 |  
| PSAM1 | PSAM slot 1 |  
| PSAM2 | PSAM slot 1 |  
| PSAM3 | PSAM slot 1 |  
| RF | Contactless card slot |  
| SWIPE | Magnetic stripe card slot |  
| Enumeration Name | Description |  
| --- | --- |  
| TYPE\_A\_CPU | |  
| TYPE\_B\_CPU | |  
| S50 | |  
| FELICA | |  
| S70 | |  
| ULTRALIGHT | |  
| MEMORY\_OTHER | |  
| S50\_PRO | |  
| S70\_PRO | |

## Página 96

SmartPos API Reference Manual: 09/08/21  
int contactlessAppendAidIntoKernel(EmvCardBrandEnum emvCardBrandEnum, byte  
aidLen, byte[] aid);  
Parameters:  
Parameter Description  
emvCardBrandEnum Card brand, such as : VISA, MASTER, JCB, UNION PAY, AMEX, DIS, PURE…  
aidLen The length of the aid  
aid aid  
EmvCardBrandEnum  
Enumeration Name Description  
EMV\_CARD\_BRAND\_VISA VISA  
EMV\_CARD\_BRAND\_MASTER MASTER  
EMV\_CARD\_BRAND\_AMEX AMEX  
EMV\_CARD\_BRAND\_RUPAY RUPAY  
EMV\_CARD\_BRAND\_UNIONPAY UNION PAY(UPI)  
EMV\_CARD\_BRAND\_DPAS DPAS: Discover , Diners  
EMV\_CARD\_BRAND\_JCB JCB  
EMV\_CARD\_BRAND\_PURE PURE  
EMV\_CARD\_BRAND\_MIR MIR  
EMV\_CARD\_BRAND\_MB MB  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.8.44 getPayWaveResult  
get the TVR and TSI of paywave flow.  
Confidential 96  
| Parameter | Description |  
| --- | --- |  
| emvCardBrandEnum | Card brand, such as : VISA, MASTER, JCB, UNION PAY, AMEX, DIS, PURE… |  
| aidLen | The length of the aid |  
| aid | aid |  
| Enumeration Name | Description |  
| --- | --- |  
| EMV\_CARD\_BRAND\_VISA | VISA |  
| EMV\_CARD\_BRAND\_MASTER | MASTER |  
| EMV\_CARD\_BRAND\_AMEX | AMEX |  
| EMV\_CARD\_BRAND\_RUPAY | RUPAY |  
| EMV\_CARD\_BRAND\_UNIONPAY | UNION PAY(UPI) |  
| EMV\_CARD\_BRAND\_DPAS | DPAS: Discover , Diners |  
| EMV\_CARD\_BRAND\_JCB | JCB |  
| EMV\_CARD\_BRAND\_PURE | PURE |  
| EMV\_CARD\_BRAND\_MIR | MIR |  
| EMV\_CARD\_BRAND\_MB | MB |

## Página 97

SmartPos API Reference Manual: 09/08/21  
PayWaveResultEntity getPayWaveResult();  
Return :  
PayWaveResultEntity  
Parameter Description  
tvr TVR  
tsi TSI  
3.9 EMV class(Emvhandler) Deprecated  
Please note: All the Emvhandler method, do not recommend use it anymore.  
The EMV class is responsible for managing the EMV operation of the POS.  
Get the object of the EMV class:  
EmvHandler EmvHandler = deviceEngine getEmvHandler (String appId ).;  
Parameters:  
Parameter Description  
appId Application ID is mainly used to distinguish between aid and capk storage  
paths  
This module operates using the basic flow chart:  
Confidential 97  
| Parameter | Description |  
| --- | --- |  
| tvr | TVR |  
| tsi | TSI |  
| Parameter | Description |  
| --- | --- |  
| appId | Application ID is mainly used to distinguish between aid and capk storage paths |

## Página 98

SmartPos API Reference Manual: 09/08/21  
Start Confirm where eCash is used and then  
callback on setconfirmEcswitch  
EMV transaction  
response  
Get Context  
After confirming card no callback  
Obtain EMV object  
onsetconfirmcardnoresponse  
Use initermocnfig to set  
After inputting key call  
terminal attributem initialized  
onsetpininputresponse  
EMV kernel or use default  
After verifying certificate call  
Call emvprocess to start EMV onsetcertverifyyresponse  
After handling terminal risk call  
After choosing app list, call  
onSetOnlineprocrepsonse  
onsetselappresponse  
After return call  
After inputting amount, call  
onsetonlinerpcoresponse  
onsetrequestamount response  
Confidential 98

## Página 99

SmartPos API Reference Manual: 09/08/21  
3.9.1 delAllAid  
Remove all AIDs.  
Public void delAllAid ();  
Parameters: None  
Return Value: None  
3.9.2 delOneAid  
Delete an AID.  
Public boolean delOneAid (byte [] aid);  
Parameters:  
Parameter Description  
aid Enter aid  
Return Value:  
True success  
False failure  
3.9.3 delAllCapk  
Remove all CAPK.  
Public void delAllCapk ();  
Parameters: None  
Return Value: None  
3.9.4 delOneCapk  
Delete a CAPK.  
Public boolean delOneCapk (byte [] rid, int capkIdx);  
Parameters:  
Parameter Description  
rid Enter rid  
Confidential 99  
| Parameter | Description |  
| --- | --- |  
| aid | Enter aid |  
| Parameter | Description |  
| --- | --- |  
| rid | Enter rid |

## Página 100

SmartPos API Reference Manual: 09/08/21  
capkIdx capk Index  
Return Value:  
True success  
False failure  
3.9.5 setAidParaList  
Set the AID.  
public int setAidParaList(List aidParaTlvList);  
Parameters:  
Parameter Description  
aidParaTlvList Aid list  
AidEntity  
attribute Description  
String aid Application ID  
int asi Application selection indicator  
2- needn't match exactly(partial match up to the  
length);  
3- match exactly  
String tacDefault Terminal Action Code – Default  
String tacOnline Terminal Action Code – Online  
String tacDenial Terminal Action Code – Denial  
String appVerNum Application Version Number  
String DDOL DDOL  
long threshold Threshold value for biased random selection  
int maxTargetPercent The maximum target percentage to be used for  
biased random selection  
Confidential 100  
| capkIdx | capk Index |  
| --- | --- |  
| Parameter | Description |  
| --- | --- |  
| aidParaTlvList | Aid list |  
| attribute | Description |  
| --- | --- |  
| String aid | Application ID |  
| int asi | Application selection indicator 2- needn't match exactly(partial match up to the length); 3- match exactly |  
| String tacDefault | Terminal Action Code – Default |  
| String tacOnline | Terminal Action Code – Online |  
| String tacDenial | Terminal Action Code – Denial |  
| String appVerNum | Application Version Number |  
| String DDOL | DDOL |  
| long threshold | Threshold value for biased random selection |  
| int maxTargetPercent | The maximum target percentage to be used for biased random selection |

## Página 101

SmartPos API Reference Manual: 09/08/21  
int targetPercent The target percentage to be used for random  
selection  
int onlinePinCap Terminal online Pin capability  
long floorLimit  
long transLimit  
long contactlessCvmLimit  
long contactlessTransLimit  
long contactlessFloorLimit  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.6 setAidParaList  
Set the AID.  
Public int setAidParaList (List aidParaTlvList);  
Parameters:  
Parameter Description  
aidParaTlvList Enter the number of aid data list, such as:  
aidParaTlvList.add(ByteUtils.hexString2ByteArray("9F0607A0000000043060DF010  
1009F08020002DF1105FC5058A000DF1205F85058F800DF130504000000009F1B0  
400000000DF150400000000DF160199DF170199DF14039F3704DF180101DF20060  
00999999999"));  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.7 setAidParaList  
Confidential 101  
| int targetPercent | The target percentage to be used for random selection |  
| --- | --- |  
| int onlinePinCap | Terminal online Pin capability |  
| long floorLimit | |  
| long transLimit | |  
| long contactlessCvmLimit | |  
| long contactlessTransLimit | |  
| long contactlessFloorLimit | |  
| Parameter | Description |  
| --- | --- |  
| aidParaTlvList | Enter the number of aid data list, such as: aidParaTlvList.add(ByteUtils.hexString2ByteArray("9F0607A0000000043060DF010 1009F08020002DF1105FC5058A000DF1205F85058F800DF130504000000009F1B0 400000000DF150400000000DF160199DF170199DF14039F3704DF180101DF20060 00999999999")); |

## Página 102

SmartPos API Reference Manual: 09/08/21  
Set the AID.  
public int setAidParaList(List aidParaTlvList);  
Parameters:  
Parameter Description  
aidParaTlvList Enter the number of aid data list, such as:  
aidParaTlvList.add("9F0607A0000000043060DF01  
01009F08020002DF1105FC5058A000DF1205F850  
58F800DF130504000000009F1B0400000000DF15  
0400000000DF160199DF170199DF14039F3704DF  
180101DF2006000999999999");  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.8 setCAPKList  
Set CAPK.  
public int setCAPKList(List capkTlvList);  
参数项：  
Parameter Description  
capkTlvList Capk list  
CapkEntity  
attribute Description  
String rid Registered Application Identifier  
int capkIdx Unique CA public key index number  
int hashInd Cryptographic algorithm ID used to generate the  
CAPK  
String modulus CA Public Key modulus  
String exponent CA Public Key exponent  
String checkSum CA Public Key checkSum  
String expireDate CA Public Key expireDate(YYYYMMDD)  
Confidential 102  
| Parameter | Description |  
| --- | --- |  
| aidParaTlvList Enter t | he number of aid data list, such as: aidParaTlvList.add("9F0607A0000000043060DF01 01009F08020002DF1105FC5058A000DF1205F850 58F800DF130504000000009F1B0400000000DF15 0400000000DF160199DF170199DF14039F3704DF 180101DF2006000999999999"); |  
| Parameter | Description |  
| --- | --- |  
| capkTlvList | Capk list |  
| attribute | Description |  
| --- | --- |  
| String rid | Registered Application Identifier |  
| int capkIdx | Unique CA public key index number |  
| int hashInd | Cryptographic algorithm ID used to generate the CAPK |  
| String modulus | CA Public Key modulus |  
| String exponent | CA Public Key exponent |  
| String checkSum | CA Public Key checkSum |  
| String expireDate | CA Public Key expireDate(YYYYMMDD) |

## Página 103

SmartPos API Reference Manual: 09/08/21  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.9 setCAPKList  
Set CAPK.  
Public int setCAPKList (List capkTlvList);  
Parameters:  
Parameter Description  
capkTlvList Enter multiple capk data list, such as:  
capkTlvList.add (ByteUtils.hexString2ByteArray  
("9F0605A0000000659F220109DF05083230303931323331DF060101DF070101DF02  
8180B72A8FEF5B27F2B550398FDCC256F714BAD497FF56094B7408328CB626AA6F0  
E6A9DF8388EB9887BC930170BCC1213E90FC070D52C8DCD0FF9E10FAD36801FE93F  
C998A721705091F18BC7C98241CADC15A2B9DA7FB963142C0AB640D5D0135E77EB  
AE95AF1B4FEFADCF9C012366BDDA0455C1564A68810D7127676D493890BDDF0401  
03DF03144410C6D51C2F83ADFD92528FA6E38A32DF048D0A"));  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.10 setCAPKList  
Set CAPK.  
public int setCAPKList(List capkTlvList);  
Parameters:  
Parameter Description  
capkTlvList Enter multiple capk data list, such as:  
capkTlvList.add("9F0605A0000000659F220109DF050  
83230303931323331DF060101DF070101DF028180B  
Confidential 103  
| Parameter | Description |  
| --- | --- |  
| capkTlvList | Enter multiple capk data list, such as: capkTlvList.add (ByteUtils.hexString2ByteArray ("9F0605A0000000659F220109DF05083230303931323331DF060101DF070101DF02 8180B72A8FEF5B27F2B550398FDCC256F714BAD497FF56094B7408328CB626AA6F0 E6A9DF8388EB9887BC930170BCC1213E90FC070D52C8DCD0FF9E10FAD36801FE93F C998A721705091F18BC7C98241CADC15A2B9DA7FB963142C0AB640D5D0135E77EB AE95AF1B4FEFADCF9C012366BDDA0455C1564A68810D7127676D493890BDDF0401 03DF03144410C6D51C2F83ADFD92528FA6E38A32DF048D0A")); |  
| Parameter | Description |  
| --- | --- |  
| capkTlvList | Enter multiple capk data list, such as: capkTlvList.add("9F0605A0000000659F220109DF050 83230303931323331DF060101DF070101DF028180B |

## Página 104

SmartPos API Reference Manual: 09/08/21  
72A8FEF5B27F2B550398FDCC256F714BAD497FF560  
94B7408328CB626AA6F0E6A9DF8388EB9887BC9301  
70BCC1213E90FC070D52C8DCD0FF9E10FAD36801FE  
93FC998A721705091F18BC7C98241CADC15A2B9DA  
7FB963142C0AB640D5D0135E77EBAE95AF1B4FEFA  
DCF9C012366BDDA0455C1564A68810D7127676D49  
3890BDDF040103DF03144410C6D51C2F83ADFD925  
28FA6E38A32DF048D0A");  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.11 setDynamicReaderLimitList  
Set DRL for paywave  
public int setDynamicReaderLimitList(List drlEntityList)  
Parameters:  
Parameter Description  
drlEntityList  
DRL list  
DynamicReaderLimitEntity  
attribute Description  
byte[] appProgID  
boolean statusCheck  
boolean authOfZeroCheck  
byte authOfZeroCheckOption;  
boolean readerContactlessTransLimitCheck;  
boolean readerCVMReqLimitCheck;  
boolean readerContactlessFloorLimitCheck;  
private boolean drlSupport;  
Confidential 104  
| | 72A8FEF5B27F2B550398FDCC256F714BAD497FF560 94B7408328CB626AA6F0E6A9DF8388EB9887BC9301 70BCC1213E90FC070D52C8DCD0FF9E10FAD36801FE 93FC998A721705091F18BC7C98241CADC15A2B9DA 7FB963142C0AB640D5D0135E77EBAE95AF1B4FEFA DCF9C012366BDDA0455C1564A68810D7127676D49 3890BDDF040103DF03144410C6D51C2F83ADFD925 28FA6E38A32DF048D0A"); |  
| --- | --- |  
| Parameter | Description |  
| --- | --- |  
| drlEntityList | DRL list |  
| attribute | Description |  
| --- | --- |  
| byte[] appProgID | |  
| boolean statusCheck | |  
| boolean authOfZeroCheck | |  
| byte authOfZeroCheckOption; | |  
| boolean readerContactlessTransLimitCheck; | |  
| boolean readerCVMReqLimitCheck; | |  
| boolean readerContactlessFloorLimitCheck; | |  
| private boolean drlSupport; | |

## Página 105

SmartPos API Reference Manual: 09/08/21  
byte[] readerContactlessTransLimit;  
byte[] readerCVMReqLimit;  
byte[] readerContactlessFloorLimit;  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.12 setDynamicReaderLimitListForExpressPay  
Set DRL for Amex Expresspay  
public int setDynamicReaderLimitListForExpressPay (List drlEntityList)  
Parameters:  
Parameter Description  
drlEntityList  
DRL list  
DynamicReaderLimitEntity  
attribute Description  
byte[] appProgID Application Prog ID  
boolean statusCheck statusCheck  
boolean authOfZeroCheck  
byte authOfZeroCheckOption;  
boolean readerContactlessTransLimitCheck;  
boolean readerCVMReqLimitCheck;  
boolean readerContactlessFloorLimitCheck;  
private boolean drlSupport;  
byte[] readerContactlessTransLimit;  
byte[] readerCVMReqLimit;  
byte[] readerContactlessFloorLimit;  
Return Value:  
Confidential 105  
| byte[] readerContactlessTransLimit; | |  
| --- | --- |  
| byte[] readerCVMReqLimit; | |  
| byte[] readerContactlessFloorLimit; | |  
| Parameter | Description |  
| --- | --- |  
| drlEntityList | DRL list |  
| attribute | Description |  
| --- | --- |  
| byte[] appProgID | Application Prog ID |  
| boolean statusCheck | statusCheck |  
| boolean authOfZeroCheck | |  
| byte authOfZeroCheckOption; | |  
| boolean readerContactlessTransLimitCheck; | |  
| boolean readerCVMReqLimitCheck; | |  
| boolean readerContactlessFloorLimitCheck; | |  
| private boolean drlSupport; | |  
| byte[] readerContactlessTransLimit; | |  
| byte[] readerCVMReqLimit; | |  
| byte[] readerContactlessFloorLimit; | |

## Página 106

SmartPos API Reference Manual: 09/08/21  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.13 initTermConfig  
Allows the user to set the terminal personalization attribute, initialize the EMV kernel, and use the EMV  
kernel default attribute if the user does not call it. (Not recommended, please use method setTlv instead  
of this method)  
Public int initTermConfig (byte [] cfgTlv);  
Parameters:  
Parameter Description  
cfgTlv Standard tlv data stream  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.14 emvProcess  
Start emv process  
Public int emvProcess (emvTransDataEntity transData, OnEmvProcessListener listener);  
Parameters:  
Parameter Description  
transData EMV transactions Entity Info  
listener EMV flow monitor interfaces  
EmvTransDataEntity  
Attributes Description  
EmvAlgorithmTypeEnum algType Type RSA algorithm or SM2, the default RSA  
Confidential 106  
| Parameter | Description |  
| --- | --- |  
| cfgTlv | Standard tlv data stream |  
| Parameter | Description |  
| --- | --- |  
| transData | EMV transactions Entity Info |  
| listener | EMV flow monitor interfaces |  
| Attributes | Description |  
| --- | --- |  
| EmvAlgorithmTypeEnum algType | Type RSA algorithm or SM2, the default RSA |

## Página 107

SmartPos API Reference Manual: 09/08/21  
EmvTransFlowEnum procType EMV Process Type  
String traceNo Serial number, length 8  
String transAmt Amount, length 12  
String cashbackAmt Cash back amount, length 12  
String transDate Transaction date MMDD, length 4  
String transTime Transaction date HHMMSS, length 6  
byte[] merName Business name  
String merId Business number, length 15  
String termId Terminal number, length 8  
byte B9C Transaction Type, sale-0x00, refund-0x20…  
boolean isSupportEC Whether support e-cash(only sued for union pay in china  
market)  
EMVChannelTypeEnum channelType Card approach, contact or contactless  
boolean isQpbocForceLine union pay whether to force go online  
boolean isDefaultEC When set to support EC is true, the default is e-cash; when  
false, then callback the method to let user to select whether  
to use electronic cash(only sued for union pay in china market)  
isSupportCDCVM union pay support CDCVM  
isQpbocForGlobal Union pay contactless check CVM limit for excute CVM  
method.(not force prompt online pin).  
EMVAlgorithmTypeEnum  
Enumeration Name Description  
RSA RSA  
SM2 Country code  
EMVTransFlowEnum  
Enumeration Name Description  
FULL Standard full process  
SIMPLE Simple processonly confirms the callback  
number, then  
directlyOnFinish ends EMV process  
Confidential 107  
| EmvTransFlowEnum procType | EMV Process Type |  
| --- | --- |  
| String traceNo | Serial number, length 8 |  
| String transAmt | Amount, length 12 |  
| String cashbackAmt | Cash back amount, length 12 |  
| String transDate | Transaction date MMDD, length 4 |  
| String transTime | Transaction date HHMMSS, length 6 |  
| byte[] merName | Business name |  
| String merId | Business number, length 15 |  
| String termId | Terminal number, length 8 |  
| byte B9C | Transaction Type, sale-0x00, refund-0x20… |  
| boolean isSupportEC | Whether support e-cash(only sued for union pay in china market) |  
| EMVChannelTypeEnum channelType | Card approach, contact or contactless |  
| boolean isQpbocForceLine | union pay whether to force go online |  
| boolean isDefaultEC | When set to support EC is true, the default is e-cash; when false, then callback the method to let user to select whether to use electronic cash(only sued for union pay in china market) |  
| isSupportCDCVM | union pay support CDCVM |  
| isQpbocForGlobal | Union pay contactless check CVM limit for excute CVM method.(not force prompt online pin). |  
| Enumeration Name | Description |  
| --- | --- |  
| RSA | RSA |  
| SM2 | Country code |  
| Enumeration Name | Description |  
| --- | --- |  
| FULL | Standard full process |  
| SIMPLE | Simple processonly confirms the callback number, then directlyOnFinish ends EMV process |

## Página 108

SmartPos API Reference Manual: 09/08/21  
QPASS qpboc flow only confirms the callback  
number, then directly OnFinish ends EMV  
process  
EMVChannelTypeEnum  
Enumeration Name Description  
FROM\_ICC Contact  
FROM\_PICC Contactless  
Return Value:  
SdkResult.Success success execution listener callback  
SdkResult.Param\_In\_Invalid illegal Parameter  
3.9.15 onSetSelAppResponse  
After executing the OnEMVProcessListener. OnSelApp method, call the EMV kernel to continue the  
process.  
Public void onSetSelAppResponse (int selResult);  
Parameters:  
Parameter Description  
selResult After selecting the AID index number, the index starts at 1; the  
method is performed by onSelApp after obtained.  
Return Value: None  
3.9.16 onSetAfterFinalSelectedAppResponse  
After executing the OnEMVProcessListener. onAfterFinalSelectedApp method, call the EMV kernel to  
continue the process.  
Public void onSetAfterFinalSelectedAppResponse (boolean isSuccess);  
Parameters:  
Parameter Description  
isSuccess Default value: true.  
The result of final select application.  
Return Value: None  
Confidential 108  
| QPASS | qpboc flow only confirms the callback number, then directly OnFinish ends EMV process |  
| --- | --- |  
| Enumeration Name | Description |  
| --- | --- |  
| FROM\_ICC | Contact |  
| FROM\_PICC | Contactless |  
| Parameter | Description |  
| --- | --- |  
| selResult | After selecting the AID index number, the index starts at 1; the method is performed by onSelApp after obtained. |  
| Parameter | Description |  
| --- | --- |  
| isSuccess | Default value: true. The result of final select application. |

## Página 109

SmartPos API Reference Manual: 09/08/21  
3.9.17 onSetRequestAmountResponse  
After executing the OnEmvProcessListener. OnRequestAmount method, call the EMV kernel to continue  
the process.  
Public void onSetRequestAmountResponse (String amount);  
Parameters:  
Parameter Description  
amount Amount length 12, prepend with 0s to make it 12 digits long.  
Return Value: None  
3.9.18 onSetConfirmEcSwitchResponse  
After executing the OnEMVProcessListener. OnConfirmEcSwitch method, call the EMV kernel to  
continue the process.  
Public void onSetConfirmEcSwitchResponse (boolean isConfirm);  
Parameters:  
Parameter Description  
isConfirm Whether to use electronic cash, true: yes, false: no  
Return Value: None  
3.9.19 onSetConfirmCardNoResponse  
After executing the OnEmvProcessListener. OnConfirmCardNo method, call the EMV kernel to continue  
the process.  
Public void onSetConfirmCardNoResponse (boolean isConfirm);  
Parameters:  
Parameter Description  
isConfirm Are you sure, true: yes, false: no  
Return Value: None  
3.9.20 onSetPinInputResponse  
After executing the OnEMVProcessListener. OnCardHolderInputPin method, call the EMV kernel to  
continue the process.  
Confidential 109  
| Parameter | Description |  
| --- | --- |  
| amount | Amount length 12, prepend with 0s to make it 12 digits long. |  
| Parameter | Description |  
| --- | --- |  
| isConfirm | Whether to use electronic cash, true: yes, false: no |  
| Parameter | Description |  
| --- | --- |  
| isConfirm | Are you sure, true: yes, false: no |

## Página 110

SmartPos API Reference Manual: 09/08/21  
Public void onSetPinInputResponse (boolean isConfirm, boolean isBypass);  
Parameters:  
Parameter Description  
isConfirm Whether the Enter key is pressed  
isBypass If no password is entered, press the Enter key  
Return Value: None  
3.9.21 onsetCertVerifyResponse  
After executing the OnEMVProcessListener. OnCertVerify method, call the EMV kernel to continue the  
process.  
Public void onSetCertVerifyResponse (boolean isVerify);  
Parameters:  
Parameter Description  
isVerify Are you sure, true: yes, false: no  
Return Value: None  
3.9.22 onSetReadCardAgainResponse  
After executing OnEmvProcessListener. onReadCardAgain method，call the EMV kernel to continue  
the process.  
public void onSetReadCardAgainResponse(boolean isSuccess);  
Parameters:  
Parameter Description  
isSuccess isSuccess，true:yes，false:no  
Return Value: None  
3.9.23 onSetOnlineProcResponse  
After executing the OnEmvProcessListener. OnOnlineProc method, call the EMV kernel to take the  
secondary authorization.  
public void onSetOnlineProcResponse (int retCode, EmvOnlineResultEntity result);  
Confidential 110  
| Parameter | Description |  
| --- | --- |  
| isConfirm | Whether the Enter key is pressed |  
| isBypass | If no password is entered, press the Enter key |  
| Parameter | Description |  
| --- | --- |  
| isVerify | Are you sure, true: yes, false: no |  
| Parameter | Description |  
| --- | --- |  
| isSuccess | isSuccess，true:yes，false:no |

## Página 111

SmartPos API Reference Manual: 09/08/21  
Parameters:  
Parameter Description  
retCode SdkResult.Success: connect to the host successfully.  
SdkResult.Fail: unable connect to the host.  
result EmvOnlineResultEntity, EMV online results  
EmvOnlineResultEntity  
Attributes Description  
String rejCode Host respond with transaction response codes  
String authCode Host respond with Transaction Authorization  
Code  
Byte [] recvField55 Host respond 55 field data  
Return Value: None  
3.9.24 onSetPromptResponse  
After executing OnEmvProcessListener. onPrompt method，call the EMV kernel to continue the  
process.  
public void onSetPromptResponse (boolean isSuccess);  
Parameters:  
Parameter Description  
isSuccess isSuccess，true:yes，false:no  
Return Value: None  
3.9.25 onSetRemoveCardResponse  
After executing OnEmvProcessListener. onRemoveCard method，call the EMV kernel to continue the  
process.  
public void onSetRemoveCardResponse (boolean isSuccess);  
Parameters:  
Parameter Description  
Confidential 111  
| Parameter | Description |  
| --- | --- |  
| retCode | SdkResult.Success: connect to the host successfully. SdkResult.Fail: unable connect to the host. |  
| result | EmvOnlineResultEntity, EMV online results |  
| Attributes | Description |  
| --- | --- |  
| String rejCode | Host respond with transaction response codes |  
| String authCode | Host respond with Transaction Authorization Code |  
| Byte [] recvField55 | Host respond 55 field data |  
| Parameter | Description |  
| --- | --- |  
| isSuccess | isSuccess，true:yes，false:no |  
| Parameter | Description |  
| --- | --- |

## Página 112

SmartPos API Reference Manual: 09/08/21  
isSuccess isSuccess，true:yes，false:no  
Return Value: None  
3.9.26 getTlv  
Get tag.  
Public byte [] getTlv (byte [] tag, EmvDataSourceEnum pathId);  
Parameters:  
Parameter Description  
tag tag value  
pathId tag source  
EmvDataSourceEnum  
Enumeration Name Description  
FROM\_KERNEL Data sources kernel  
FORM\_CARD Data sources cards  
Return Value:  
Tlv successful Return Value  
Else return null  
3.9.27 getTlvByTags  
public String getTlvByTags(String[] tags);  
Parameters:  
Parameter Description  
tags Tag such as: String[] TAGS = {"9f26", "9f27",  
"9f10", "9f37", "9f36", "95", "9a", "9c", "9f02",  
"5f2a", "82", "9f1a", "9f03","9f33", "9f34", "9f35",  
"9f1e", "9f09", "84", "9f41"}  
Return Value:  
Confidential 112  
| isSuccess | isSuccess，true:yes，false:no |  
| --- | --- |  
| Parameter | Description |  
| --- | --- |  
| tag | tag value |  
| pathId | tag source |  
| Enumeration Name | Description |  
| --- | --- |  
| FROM\_KERNEL | Data sources kernel |  
| FORM\_CARD | Data sources cards |  
| Parameter | Description |  
| --- | --- |  
| tags | Tag such as: String[] TAGS = {"9f26", "9f27", "9f10", "9f37", "9f36", "95", "9a", "9c", "9f02", "5f2a", "82", "9f1a", "9f03","9f33", "9f34", "9f35", "9f1e", "9f09", "84", "9f41"} |

## Página 113

SmartPos API Reference Manual: 09/08/21  
Tlv successful Return string Value  
Else return null  
3.9.28 setTlv  
Settings tag.  
public int setTlv (byte [] tag, byte [] value);  
Parameters:  
Parameter Description  
tag tag value  
value data  
Return Value:  
SdkResult.Success success  
SdkResult.Fail failure  
SdkResult.Param\_In\_Invalid Parameter error  
3.9.29 getEMVCardLog  
Read the log, this method is finished after the callback to onFinish method.  
public int getEmvCardLog (EmvChannelTypeEnum channelType, OnEmvProcessListener listener);  
Parameters:  
Parameter Description  
channelType Channel Type  
listener Callback  
EmvChannelTypeEnum  
Enumeration Name Description  
FROM\_ICC Contact  
FROM\_PICC Contactless  
Return Value:  
SdkResult.Success successful execution of listener callback interface  
SdkResult.Param\_In\_Invalid illegal Parameter  
Confidential 113  
| Parameter | Description |  
| --- | --- |  
| tag | tag value |  
| value | data |  
| Parameter | Description |  
| --- | --- |  
| channelType | Channel Type |  
| listener | Callback |  
| Enumeration Name | Description |  
| --- | --- |  
| FROM\_ICC | Contact |  
| FROM\_PICC | Contactless |

## Página 114

SmartPos API Reference Manual: 09/08/21  
SdkResult.Fail failure  
3.9.30 Clear the Log  
Clear the log.  
public int clearLog();  
Parameters: None  
Return Value:  
SdkResult.Success success  
SdkResult.Fail failure  
3.9.31 EMVGetEcBalance  
Read electronic cash balance.  
public int emvGetEcBalance (EmvChannelTypeEnum channelType, OnEmvProcessListener listener);  
Parameters:  
Parameter Description  
channelType Channel Type  
listener Callback  
EmvChannelTypeEnum  
Enumeration Name Description  
FROM \_ICC Contact  
FROM\_PICC Contactless  
Return Value:  
SdkResult.Success successful execution of listener callback interface  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.32 EMVProcessCancel  
Cancel EMV process.  
public void emvProcessCancel ();  
Parameters: None  
Confidential 114  
| Parameter | Description |  
| --- | --- |  
| channelType | Channel Type |  
| listener | Callback |  
| Enumeration Name | Description |  
| --- | --- |  
| FROM \_ICC | Contact |  
| FROM\_PICC | Contactless |

## Página 115

SmartPos API Reference Manual: 09/08/21  
Return Value: None  
3.9.33 emvDebugLog  
enable EMV log, default false  
public void emvDebugLog(boolean isEnable);  
Parameters:  
Parameter Description  
isEnable  
True ，false  
Return Value: None  
3.9.34 getEmvContactlessMode  
get EMV contactless flow mode, EMV mode or MSD mode, should be called in method  
onOnlineProc or onFinish method  
public EmvModeEnum getEmvContactlessMode();  
Return Value:  
EmvModeEnum  
Enumeration Name Description  
EMV EMV mode  
MSD MSD mode  
3.9.35 getAidListNum  
get aid list number  
public int getAidListNum();  
Return Value:  
Number of aid list  
Confidential 115  
| Parameter | Description |  
| --- | --- |  
| isEnable | True ，false |  
| Enumeration Name | Description |  
| --- | --- |  
| EMV | EMV mode |  
| MSD | MSD mode |

## Página 116

SmartPos API Reference Manual: 09/08/21  
3.9.36 getAidList  
get aid list  
public List getAidList();  
AidEntity  
Attributes Description  
String aid Application ID  
int asi Application selection indicator  
4- needn't match exactly(partial match up to the  
length);  
5- match exactly  
String tacDefault Terminal Action Code – Default  
String tacOnline Terminal Action Code – Online  
String tacDenial Terminal Action Code – Denial  
String appVerNum Application Version Number  
String DDOL DDOL  
long threshold Threshold value for biased random selection  
int maxTargetPercent The maximum target percentage to be used for  
biased random selection  
int targetPercent The target percentage to be used for random  
selection  
int onlinePinCap Terminal online Pin capability  
long floorLimit  
long transLimit  
long contactlessCvmLimit  
long contactlessTransLimit  
long contactlessFloorLimit  
Return Value:  
Success return aid list  
Fail return null  
3.9.37 getCapkListNum  
get capk list number  
Confidential 116  
| Attributes | Description |  
| --- | --- |  
| String aid | Application ID |  
| int asi | Application selection indicator 4- needn't match exactly(partial match up to the length); 5- match exactly |  
| String tacDefault | Terminal Action Code – Default |  
| String tacOnline | Terminal Action Code – Online |  
| String tacDenial | Terminal Action Code – Denial |  
| String appVerNum | Application Version Number |  
| String DDOL | DDOL |  
| long threshold | Threshold value for biased random selection |  
| int maxTargetPercent | The maximum target percentage to be used for biased random selection |  
| int targetPercent | The target percentage to be used for random selection |  
| int onlinePinCap | Terminal online Pin capability |  
| long floorLimit | |  
| long transLimit | |  
| long contactlessCvmLimit | |  
| long contactlessTransLimit | |  
| long contactlessFloorLimit | |

## Página 117

SmartPos API Reference Manual: 09/08/21  
public int getCapkListNum();  
Return Value:  
Number of capk list  
3.9.38 getCapkList  
get capk list  
public List getCapkList();  
CapkEntity  
Attributes Description  
String rid Registered Application Identifier  
int capkIdx Unique CA public key index number  
int hashInd Cryptographic algorithm ID used to generate the  
CAPK  
String modulus CA Public Key modulus  
String exponent CA Public Key exponent  
String checkSum CA Public Key checkSum  
String expireDate CA Public Key expireDate(MMYY)  
Return Value:  
Success return capk list  
Fail return null  
3.9.39 newDelAllAid  
Pure , MIR kernel API, delete all the AID  
public void newDelAllAid();  
Parameters: None  
Return Value: None  
3.9.40 newDelOneAid  
Confidential 117  
| Attributes | Description |  
| --- | --- |  
| String rid | Registered Application Identifier |  
| int capkIdx | Unique CA public key index number |  
| int hashInd | Cryptographic algorithm ID used to generate the CAPK |  
| String modulus | CA Public Key modulus |  
| String exponent | CA Public Key exponent |  
| String checkSum | CA Public Key checkSum |  
| String expireDate | CA Public Key expireDate(MMYY) |

## Página 118

SmartPos API Reference Manual: 09/08/21  
Pure , MIR kernel API, delete one AID  
public boolean newDelOneAid(byte[] aid);  
parameter：  
Attributes Description  
byte[] aid aid  
Return Value:  
ture delete success  
false delete failed  
3.9.41 newDelAllCapk  
Pure , MIR kernel API, delete all CAPK  
public void newDelAllCapk();  
Parameters: None  
Return Value: None  
3.9.42 newDelOneCapk  
Pure , MIR kernel API, delete one CAPK  
public boolean newDelOneCapk(byte[] rid,int capkIdx);  
parameter：  
Attributes Description  
rid rid  
capkIdx Capk index  
Return Value:  
ture delete success  
false delete failed  
Confidential 118  
| Attributes | Description |  
| --- | --- |  
| byte[] aid | aid |  
| Attributes | Description |  
| --- | --- |  
| rid | rid |  
| capkIdx | Capk index |

## Página 119

SmartPos API Reference Manual: 09/08/21  
3.9.43 newSetAidParaList  
Pure , MIR kernel API, set AID list  
public int newSetAidParaList(List aidParaTlvList);  
parameter：  
Attributes Description  
aidParaTlvList Aid list：  
aidParaTlvList.add(ByteUtils.hexString2ByteArray("9F  
0607A0000000043060DF0101009F08020002DF1105  
FC5058A000DF1205F85058F800DF13050400000000  
9F1B0400000000DF150400000000DF160199DF1701  
99DF14039F3704DF180101DF2006000999999999"));  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.44 newSetAidParaList  
Pure , MIR kernel API, set AID list  
public int newSetAidParaList(List aidParaTlvList);  
parameter：  
Attributes Description  
aidParaTlvList Aid list：  
aidParaTlvList.add("9F0607A0000000043060DF0101  
009F08020002DF1105FC5058A000DF1205F85058F8  
00DF130504000000009F1B0400000000DF15040000  
0000DF160199DF170199DF14039F3704DF180101DF  
2006000999999999");  
Confidential 119  
| Attributes | Description |  
| --- | --- |  
| aidParaTlvList | Aid list： aidParaTlvList.add(ByteUtils.hexString2ByteArray("9F 0607A0000000043060DF0101009F08020002DF1105 FC5058A000DF1205F85058F800DF13050400000000 9F1B0400000000DF150400000000DF160199DF1701 99DF14039F3704DF180101DF2006000999999999")); |  
| Attributes | Description |  
| --- | --- |  
| aidParaTlvList | Aid list： aidParaTlvList.add("9F0607A0000000043060DF0101 009F08020002DF1105FC5058A000DF1205F85058F8 00DF130504000000009F1B0400000000DF15040000 0000DF160199DF170199DF14039F3704DF180101DF 2006000999999999"); |

## Página 120

SmartPos API Reference Manual: 09/08/21  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.45 newSetAidParaList  
Pure , MIR kernel API, set AID list  
public int newSetAidParaList(List aidParaTlvList);  
parameter：  
Attributes Description  
aidParaTlvList Aid list  
AidEntity  
Attributes Description  
String aid Application ID  
int asi Application selection indicator  
6- needn't match exactly(partial match up to the  
length);  
7- match exactly  
String tacDefault Terminal Action Code – Default  
String tacOnline Terminal Action Code – Online  
String tacDenial Terminal Action Code – Denial  
String appVerNum Application Version Number  
String DDOL DDOL  
long threshold Threshold value for biased random selection  
int maxTargetPercent The maximum target percentage to be used for  
biased random selection  
int targetPercent The target percentage to be used for random  
selection  
int onlinePinCap Terminal online Pin capability  
long floorLimit  
long transLimit  
Confidential 120  
| Attributes | Description |  
| --- | --- |  
| aidParaTlvList | Aid list |  
| Attributes | Description |  
| --- | --- |  
| String aid | Application ID |  
| int asi | Application selection indicator 6- needn't match exactly(partial match up to the length); 7- match exactly |  
| String tacDefault | Terminal Action Code – Default |  
| String tacOnline | Terminal Action Code – Online |  
| String tacDenial | Terminal Action Code – Denial |  
| String appVerNum | Application Version Number |  
| String DDOL | DDOL |  
| long threshold | Threshold value for biased random selection |  
| int maxTargetPercent | The maximum target percentage to be used for biased random selection |  
| int targetPercent | The target percentage to be used for random selection |  
| int onlinePinCap | Terminal online Pin capability |  
| long floorLimit | |  
| long transLimit | |

## Página 121

SmartPos API Reference Manual: 09/08/21  
long contactlessCvmLimit  
long contactlessTransLimit  
long contactlessFloorLimit  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.46 newSetCAPKList  
Pure , MIR kernel API, set CAPK list  
public int newSetCAPKList(List capkTlvList);  
parameter：  
Attributes Description  
capkTlvList Capk list：  
capkTlvList.add(ByteUtils.hexString2ByteArray("9F06  
05A0000000659F220109DF05083230303931323331  
DF060101DF070101DF028180B72A8FEF5B27F2B550  
398FDCC256F714BAD497FF56094B7408328CB626AA  
6F0E6A9DF8388EB9887BC930170BCC1213E90FC070  
D52C8DCD0FF9E10FAD36801FE93FC998A721705091  
F18BC7C98241CADC15A2B9DA7FB963142C0AB640D  
5D0135E77EBAE95AF1B4FEFADCF9C012366BDDA04  
55C1564A68810D7127676D493890BDDF040103DF0  
3144410C6D51C2F83ADFD92528FA6E38A32DF048D  
0A"));  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.47 newSetCAPKList  
Confidential 121  
| long contactlessCvmLimit | |  
| --- | --- |  
| long contactlessTransLimit | |  
| long contactlessFloorLimit | |  
| Attributes | Description |  
| --- | --- |  
| capkTlvList | Capk list： capkTlvList.add(ByteUtils.hexString2ByteArray("9F06 05A0000000659F220109DF05083230303931323331 DF060101DF070101DF028180B72A8FEF5B27F2B550 398FDCC256F714BAD497FF56094B7408328CB626AA 6F0E6A9DF8388EB9887BC930170BCC1213E90FC070 D52C8DCD0FF9E10FAD36801FE93FC998A721705091 F18BC7C98241CADC15A2B9DA7FB963142C0AB640D 5D0135E77EBAE95AF1B4FEFADCF9C012366BDDA04 55C1564A68810D7127676D493890BDDF040103DF0 3144410C6D51C2F83ADFD92528FA6E38A32DF048D 0A")); |

## Página 122

SmartPos API Reference Manual: 09/08/21  
Pure , MIR kernel API, set CAPK list  
public int newSetCAPKList(List capkTlvList);  
parameter：  
Attributes Description  
capkTlvList Capk list：  
capkTlvList.add("9F0605A0000000659F220109DF050  
83230303931323331DF060101DF070101DF028180B  
72A8FEF5B27F2B550398FDCC256F714BAD497FF560  
94B7408328CB626AA6F0E6A9DF8388EB9887BC9301  
70BCC1213E90FC070D52C8DCD0FF9E10FAD36801FE  
93FC998A721705091F18BC7C98241CADC15A2B9DA  
7FB963142C0AB640D5D0135E77EBAE95AF1B4FEFA  
DCF9C012366BDDA0455C1564A68810D7127676D49  
3890BDDF040103DF03144410C6D51C2F83ADFD925  
28FA6E38A32DF048D0A");  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.48 newSetCAPKList  
Pure , MIR kernel API, set CAPK list  
public int newSetCAPKList(List capkTlvList);  
parameter：  
Attributes Description  
capkTlvList Capk list  
CapkEntity  
Attributes Description  
String rid Registered Application Identifier  
int capkIdx Unique CA public key index number  
Confidential 122  
| Attributes | Description |  
| --- | --- |  
| capkTlvList | Capk list： capkTlvList.add("9F0605A0000000659F220109DF050 83230303931323331DF060101DF070101DF028180B 72A8FEF5B27F2B550398FDCC256F714BAD497FF560 94B7408328CB626AA6F0E6A9DF8388EB9887BC9301 70BCC1213E90FC070D52C8DCD0FF9E10FAD36801FE 93FC998A721705091F18BC7C98241CADC15A2B9DA 7FB963142C0AB640D5D0135E77EBAE95AF1B4FEFA DCF9C012366BDDA0455C1564A68810D7127676D49 3890BDDF040103DF03144410C6D51C2F83ADFD925 28FA6E38A32DF048D0A"); |  
| Attributes | Description |  
| --- | --- |  
| capkTlvList | Capk list |  
| Attributes | Description |  
| --- | --- |  
| String rid | Registered Application Identifier |  
| int capkIdx | Unique CA public key index number |

## Página 123

SmartPos API Reference Manual: 09/08/21  
int hashInd Cryptographic algorithm ID used to generate the  
CAPK  
String modulus CA Public Key modulus  
String exponent CA Public Key exponent  
String checkSum CA Public Key checkSum  
String expireDate CA Public Key expireDate(MMYY)  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.49 newGetAidListNum  
Pure , MIR kernel API, get AID list number  
public int getAidListNum();  
Return Value:  
Number of aid list  
3.9.50 newGetAidList  
Pure , MIR kernel API, get AID list number  
public List newGetAidList();  
AidEntity  
Attributes Description  
String aid Application ID  
int asi Application selection indicator  
8- needn't match exactly(partial match up to the  
length);  
Confidential 123  
| int hashInd | Cryptographic algorithm ID used to generate the CAPK |  
| --- | --- |  
| String modulus | CA Public Key modulus |  
| String exponent | CA Public Key exponent |  
| String checkSum | CA Public Key checkSum |  
| String expireDate | CA Public Key expireDate(MMYY) |  
| Attributes | Description |  
| --- | --- |  
| String aid | Application ID |  
| int asi | Application selection indicator 8- needn't match exactly(partial match up to the length); |

## Página 124

SmartPos API Reference Manual: 09/08/21  
9- match exactly  
String tacDefault Terminal Action Code – Default  
String tacOnline Terminal Action Code – Online  
String tacDenial Terminal Action Code – Denial  
String appVerNum Application Version Number  
String DDOL DDOL  
long threshold Threshold value for biased random selection  
int maxTargetPercent The maximum target percentage to be used for  
biased random selection  
int targetPercent The target percentage to be used for random  
selection  
int onlinePinCap Terminal online Pin capability  
long floorLimit  
long transLimit  
long contactlessCvmLimit  
long contactlessTransLimit  
long contactlessFloorLimit  
Return Value:  
Success return aid list  
Fail return null  
3.9.51 newGetCapkListNum  
Pure , MIR kernel API, get CAPK list number  
public int newGetCapkListNum();  
Return Value:  
Number of capk list  
3.9.52 newGetCapkList  
Pure , MIR kernel API, get CAPK list  
Confidential 124  
| | 9- match exactly |  
| --- | --- |  
| String tacDefault | Terminal Action Code – Default |  
| String tacOnline | Terminal Action Code – Online |  
| String tacDenial | Terminal Action Code – Denial |  
| String appVerNum | Application Version Number |  
| String DDOL | DDOL |  
| long threshold | Threshold value for biased random selection |  
| int maxTargetPercent | The maximum target percentage to be used for biased random selection |  
| int targetPercent | The target percentage to be used for random selection |  
| int onlinePinCap | Terminal online Pin capability |  
| long floorLimit | |  
| long transLimit | |  
| long contactlessCvmLimit | |  
| long contactlessTransLimit | |  
| long contactlessFloorLimit | |

## Página 125

SmartPos API Reference Manual: 09/08/21  
public List newGetCapkList();  
CapkEntity  
Attributes Description  
String rid Registered Application Identifier  
int capkIdx Unique CA public key index number  
int hashInd Cryptographic algorithm ID used to generate the  
CAPK  
String modulus CA Public Key modulus  
String exponent CA Public Key exponent  
String checkSum CA Public Key checkSum  
String expireDate CA Public Key expireDate(MMYY)  
Return Value:  
Success return capk list  
Fail return null  
3.9.53 selectAidFirst  
set which AID first select for contactless transaction  
public int selectAidFirst(boolean enable, byte aidLen, byte[] aid);  
parameter：  
Attributes Description  
enable Enable: true--first; false--default  
aidLen AID length  
aid AID  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.9.54 getSignNeed  
Confidential 125  
| Attributes | Description |  
| --- | --- |  
| String rid | Registered Application Identifier |  
| int capkIdx | Unique CA public key index number |  
| int hashInd | Cryptographic algorithm ID used to generate the CAPK |  
| String modulus | CA Public Key modulus |  
| String exponent | CA Public Key exponent |  
| String checkSum | CA Public Key checkSum |  
| String expireDate | CA Public Key expireDate(MMYY) |  
| Attributes | Description |  
| --- | --- |  
| enable | Enable: true--first; false--default |  
| aidLen | AID length |  
| aid | AID |

## Página 126

SmartPos API Reference Manual: 09/08/21  
get signature state  
public boolean getSignNeed();  
Return Value:  
ture need signature  
false not need signature  
3.9.55 setPureKernelCapab  
set pure kernel capability  
int setPureKernelCapab(byte[] capab);  
parameter：  
Attributes Description  
Capability, 5 bytes  
capab  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.Fail failure  
3.10 setSystemClock  
Set the system time.  
public void setSystemClock (Context context, String datetime);  
Parameters:  
Parameter Description  
context Context  
datetime Time format YYYYMMDDHHMMSS, the year in the range 1970-2049  
Confidential 126  
| Attributes | Description |  
| --- | --- |  
| capab | Capability, 5 bytes |  
| Parameter | Description |  
| --- | --- |  
| context | Context |  
| datetime | Time format YYYYMMDDHHMMSS, the year in the range 1970-2049 |

## Página 127

SmartPos API Reference Manual: 09/08/21  
Return Value: None  
3.11 getDeviceInfo  
get device information  
public DeviceInfo getDeviceInfo();  
DeviceInfo  
Attributes Description  
String sn Terminal serail number  
String ksn Custom Terminal serail number  
String model Terminal model , such as N5  
String osVer Os version ,such as 5.1.1  
String sdkVer Sdkversion, 2.0.7  
String firmWareVer firmWareversion ,base version  
String kernelVer linuxversion  
String vendor vendor ,such as Nexgo  
String firmWareFullVersion Full firmware version, such as “v1.2.8\_N50000001”  
EmvKernelVersionInfo emvKernelVersionInfo Emv L1 , L2 kernel version  
EmvKernelVersionInfo  
Attributes Description  
String emvContactL1KernelVersion; EMV Contact L1  
String emvContactlessL1KernelVersion; EMV Contactless L1  
String emvContactKernelVersion; EMV Contact L2  
String emvPayPassKernelVersion; Paypass version  
String emvPayWaveKernelVersion; Paywave version  
String emvExpressPayKernelVersion; Amex Expresspay version  
String emvDiscoverKernelVersion; EMV DIS version  
String emvJcbKernelVersion; EMV JCB version  
String emvUnionPayKernelVersion; EMV UPI version  
Confidential 127  
| Attributes | Description |  
| --- | --- |  
| String sn | Terminal serail number |  
| String ksn | Custom Terminal serail number |  
| String model | Terminal model , such as N5 |  
| String osVer | Os version ,such as 5.1.1 |  
| String sdkVer | Sdkversion, 2.0.7 |  
| String firmWareVer | firmWareversion ,base version |  
| String kernelVer | linuxversion |  
| String vendor | vendor ,such as Nexgo |  
| String firmWareFullVersion | Full firmware version, such as “v1.2.8\_N50000001” |  
| EmvKernelVersionInfo emvKernelVersionInfo | Emv L1 , L2 kernel version |  
| Attributes | Description |  
| --- | --- |  
| String emvContactL1KernelVersion; | EMV Contact L1 |  
| String emvContactlessL1KernelVersion; | EMV Contactless L1 |  
| String emvContactKernelVersion; | EMV Contact L2 |  
| String emvPayPassKernelVersion; | Paypass version |  
| String emvPayWaveKernelVersion; | Paywave version |  
| String emvExpressPayKernelVersion; | Amex Expresspay version |  
| String emvDiscoverKernelVersion; | EMV DIS version |  
| String emvJcbKernelVersion; | EMV JCB version |  
| String emvUnionPayKernelVersion; | EMV UPI version |  
| | |

## Página 128

SmartPos API Reference Manual: 09/08/21  
Return Value:  
successful return DeviceInfo  
else return null  
3.12 Serial class  
Serial class is responsible for managing POS serial port.  
Get the serial class objects:  
SerialPortDriver port = deviceEngine.getSerialPortDriver(int portNo);  
Parameter Description  
portNo Serial No. currently only supports the serial number 0  
This module uses the basic flow chart:  
Confidential 128  
| Parameter | Description |  
| --- | --- |  
| portNo | Serial No. currently only supports the serial number 0 |

## Página 129

SmartPos API Reference Manual: 09/08/21  
Start  
Obtain context  
Obtain serial object  
Call disconnect method,  
prevent serial port from  
disconnecting  
Call connect to connect serial  
port  
Call clrbuffer to clear buffer  
Call send method to send data  
Call recv to receive data  
Call disconnect to disconnect  
Confidential 129

## Página 130

SmartPos API Reference Manual: 09/08/21  
3.12.1 disconnect  
Disconnect.  
public int disconnect ();  
Parameters: None  
Return Value:  
SdkResult.Success off successfully  
SdkResult.SerialPort\_Port\_Not\_Open serial port is not open  
SdkResult.SerialPort\_DisConnect\_Fail serial chain disconnection failure  
3.12.2 connect  
Serial connection.  
public int connect(SerialCfgEntity entity);  
Parameters:  
Parameter Description  
entity SerialCfgEntity , Serial Info  
SerialCfgEntity  
Attributes Description  
int bauRate The baud rate in the range of (bps):  
110,300,600,1200,2400,4800, 9600,14400,56000,19200,38400,57600,115200,230400  
int dataBits Data Bits Range: 5, 6, 7, 8  
char parity Test methods in the range : 'o' odd , 'e' parity, 'n' no parity  
int stopBits Stop bit value range : 1, 2  
Return Value:  
SdkResult.Success serial connection success  
SdkResult.Param\_In\_Invalid Parameter is null, illegal Parameter  
SdkResult.SerialPort\_Invalid\_Communication\_Parameter invalid communication Parameters  
SdkResult.SerialPort\_Connect\_Fail serial connection failure  
SdkResult.Fail other errors  
3.12.3 clrBuffer  
Confidential 130  
| Parameter | Description |  
| --- | --- |  
| entity | SerialCfgEntity , Serial Info |  
| Attributes | Description |  
| --- | --- |  
| int bauRate | The baud rate in the range of (bps): 110,300,600,1200,2400,4800, 9600,14400,56000,19200,38400,57600,115200,230400 |  
| int dataBits | Data Bits Range: 5, 6, 7, 8 |  
| char parity | Test methods in the range : 'o' odd , 'e' parity, 'n' no parity |  
| int stopBits | Stop bit value range : 1, 2 |

## Página 131

SmartPos API Reference Manual: 09/08/21  
Clear the buffer.  
public void clrBuffer ();  
Parameters: None  
Return Value: None  
3.12.4 send  
Send data.  
public int send (byte [] data, int dataLen);  
Parameters:  
Parameter Description  
data Input data  
dataLen Data length Range: 1-2048 bytes ; non-blocking send  
Return Value:  
SdkResult.Success sent successfully  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.SerialPort\_Not\_Open serial port is not open  
SdkResult.SerialPort\_Send\_Fail serial data transmission failure  
SdkResult.Fail other errors  
3.12.5 recv  
Receive data.  
public int recv (byte [] buffer, int recvLen, long timeout);  
Parameters:  
Parameter Description  
buffer Buffer to receive data  
recvLen The maximum length of buffer, which is 2048 bytes  
timeout Timeout in milliseconds; recommended value 3000  
Return Value:  
Successfully received returns the length of the received data  
SdkResult.Param\_In\_Invalid illegal Parameter  
SdkResult.SerialPort\_Not\_Open serial port is not open  
Confidential 131  
| Parameter | Description |  
| --- | --- |  
| data | Input data |  
| dataLen | Data length Range: 1-2048 bytes ; non-blocking send |  
| Parameter | Description |  
| --- | --- |  
| buffer | Buffer to receive data |  
| recvLen | The maximum length of buffer, which is 2048 bytes |  
| timeout | Timeout in milliseconds; recommended value 3000 |

## Página 132

SmartPos API Reference Manual: 09/08/21  
SdkResult.SerialPort\_Timeout\_Receiving\_Data serial data receive timeout  
SdkResult.Fail other errors  
3.13 Buzzer class  
Class is responsible for managing POS buzzer.  
Get buzzer objects of class:  
Beeper beep = deviceEngine.getBeeper();  
This module uses the basic flow chart:  
Start  
Obtain context  
Obtain buzzer object  
Call beep method to drive  
beeper  
3.13.1 beep  
Drive the buzzer sound duration specified length of time.  
public void beep (int timeout);  
Parameters:  
Parameter Description  
timeout Timeout in milliseconds. Zero immediately stops  
Confidential 132  
| Parameter | Description |  
| --- | --- |  
| timeout | Timeout in milliseconds. Zero immediately stops |

## Página 133

SmartPos API Reference Manual: 09/08/21  
Return Value: None  
3.14 M1 Cards  
M1 card class is responsible for managing M1 card.  
Get M1 card class objects:  
M1CardHandler m1Card = deviceEngine.getM1CardHandler();  
This module uses the basic flow chart:  
Start  
Obtain context  
Obtain M1 card object  
Call authority method to  
verify block  
Call readblock method to  
read block data, Call  
writeblock method to write  
block data, Call operateblock  
to operate block  
3.14.1 authority  
Block certification.  
public int authority (Authentity entity) ;  
Parameters:  
Confidential 133

## Página 134

SmartPos API Reference Manual: 09/08/21  
Parameter Description  
entity AuthEntity, the authentication information class  
AuthEntity  
Attributes Description  
int blkNo Block number  
M1KeyTypeEnum keyType Key type enumeration  
byte [] pwd Password authentication  
String m1SN M1 card uid  
M1KeyTypeEnum  
Enumeration Name Description  
KEYTYPE\_A KEY A  
KEYTYPE\_B KEY B  
Return Value:  
SdkResult.Success success  
SdkResult.Device\_Not\_Ready device is not ready  
SdkResult.Param\_In\_InValid Parameter is not legitimate  
SdkResult.M1Card\_Verify\_Err M1 card authentication failure  
SdkResult.Fail other errors  
3.14.2 readBlock  
Read block data.  
public int readBlock (Blockentity entity) ;  
Parameters:  
Parameter Description  
entity BlockEntity block Info  
BlockEntity  
Attributes Description  
M1CardOperTypeEnum operType Operation enumeration  
byte [] blkData Data to be operated  
int BLKNO Block number to be operated  
int desBlkNo Destination block number  
Confidential 134  
| Parameter | Description |  
| --- | --- |  
| entity | AuthEntity, the authentication information class |  
| Attributes | Description |  
| --- | --- |  
| int blkNo | Block number |  
| M1KeyTypeEnum keyType | Key type enumeration |  
| byte [] pwd | Password authentication |  
| String m1SN | M1 card uid |  
| Enumeration Name | Description |  
| --- | --- |  
| KEYTYPE\_A | KEY A |  
| KEYTYPE\_B | KEY B |  
| Parameter | Description |  
| --- | --- |  
| entity | BlockEntity block Info |  
| Attributes | Description |  
| --- | --- |  
| M1CardOperTypeEnum operType | Operation enumeration |  
| byte [] blkData | Data to be operated |  
| int BLKNO | Block number to be operated |  
| int desBlkNo | Destination block number |

## Página 135

SmartPos API Reference Manual: 09/08/21  
M1CardOperTypeEnum  
Enumeration Name Description  
INCREMENT Increment  
DECREMENT Decrement operation  
BACKUP Backup  
Return Value:  
SdkResult.Success success  
SdkResult.Device\_Not\_Ready device is not ready  
SdkResult.Param\_In\_InValid Parameter is not legitimate  
SdkResult.Fail other errors  
3.14.3 readBlockValue  
Read block value  
public int readblockValue(Blockentity entity);  
Parameters:  
Parameter Description  
entity BlockEntity block Info  
BlockEntity  
Attributes Description  
M1CardOperTypeEnum operType Operation enumeration  
byte[] blkData Data to be operated  
int blkValue Read and write block data values in M1 card data  
format  
int BLKNO Block number to be operated  
int desBlkNo Destination block number  
M1CardOperTypeEnum  
Enumeration Name Description  
INCREMENT Increment  
DECREMENT Decrement operation  
BACKUP Backup  
Return Value:  
SdkResult.Success success  
Confidential 135  
| Enumeration Name | Description |  
| --- | --- |  
| INCREMENT | Increment |  
| DECREMENT | Decrement operation |  
| BACKUP | Backup |  
| Parameter | Description |  
| --- | --- |  
| entity | BlockEntity block Info |  
| Attributes | Description |  
| --- | --- |  
| M1CardOperTypeEnum operType | Operation enumeration |  
| byte[] blkData | Data to be operated |  
| int blkValue | Read and write block data values in M1 card data format |  
| int BLKNO | Block number to be operated |  
| int desBlkNo | Destination block number |  
| Enumeration Name | Description |  
| --- | --- |  
| INCREMENT | Increment |  
| DECREMENT | Decrement operation |  
| BACKUP | Backup |

## Página 136

SmartPos API Reference Manual: 09/08/21  
SdkResult.Device\_Not\_Ready device is not ready  
SdkResult.Param\_In\_InValid Parameter is not legitimate  
SdkResult.Fail other errors  
3.14.4 writeBlock  
Write block data.  
public int writeBlock (Blockentity entity) ;  
Parameters:  
Parameter Description  
entity BlockEntity block Info  
BlockEntity  
Attributes Description  
M1CardOperTypeEnum operType Operation enumeration  
byte [] blkData Data to be operated  
int BLKNO Block number to be operated  
int desBlkNo Destination block number  
M1CardOperTypeEnum  
Enumeration Name Description  
INCREMENT Increment  
DECREMENT Decrement operation  
BACKUP Backup  
Return Value:  
SdkResult.Success success  
SdkResult.Device\_Not\_Ready device is not ready  
SdkResult.Param\_In\_InValid Parameter is not legitimate  
SdkResult.Fail other errors  
3.14.5 writeBlockValue  
Write block value  
Confidential 136  
| Parameter | Description |  
| --- | --- |  
| entity | BlockEntity block Info |  
| Attributes | Description |  
| --- | --- |  
| M1CardOperTypeEnum operType | Operation enumeration |  
| byte [] blkData | Data to be operated |  
| int BLKNO | Block number to be operated |  
| int desBlkNo | Destination block number |  
| Enumeration Name | Description |  
| --- | --- |  
| INCREMENT | Increment |  
| DECREMENT | Decrement operation |  
| BACKUP | Backup |

## Página 137

SmartPos API Reference Manual: 09/08/21  
public int writeblock(Blockentity entity);  
Parameters:  
Parameter Description  
entity BlockEntity block Info  
BlockEntity  
Attributes Description  
M1CardOperTypeEnum operType Operation enumeration  
byte[] blkData Data to be operated  
int blkValue Read and write block data values in M1 card data  
format  
int BLKNO Block number to be operated  
int desBlkNo Destination block number  
M1CardOperTypeEnum  
Enumeration Name Description  
INCREMENT Increment  
DECREMENT Decrement operation  
BACKUP Backup  
Return Value:  
SdkResult.Success success  
SdkResult.Device\_Not\_Ready device is not ready  
SdkResult.Param\_In\_InValid Parameter is not legitimate  
SdkResult.Fail other errors  
3.14.6 operateBlock  
Operation block data.  
public int operateblock (Blockentity entity) ;  
Parameters:  
Parameter Description  
entity BlockEntity block Info  
BlockEntity  
Confidential 137  
| Parameter | Description |  
| --- | --- |  
| entity | BlockEntity block Info |  
| Attributes | Description |  
| --- | --- |  
| M1CardOperTypeEnum operType | Operation enumeration |  
| byte[] blkData | Data to be operated |  
| int blkValue | Read and write block data values in M1 card data format |  
| int BLKNO | Block number to be operated |  
| int desBlkNo | Destination block number |  
| Enumeration Name | Description |  
| --- | --- |  
| INCREMENT | Increment |  
| DECREMENT | Decrement operation |  
| BACKUP | Backup |  
| Parameter | Description |  
| --- | --- |  
| entity | BlockEntity block Info |

## Página 138

SmartPos API Reference Manual: 09/08/21  
Attributes Description  
M1CardOperTypeEnum operType Operation enumeration  
byte [] blkData Data to be operated  
int BLKNO Block number to be operated  
int desBlkNo Destination block number  
M1CardOperTypeEnum  
Enumeration Name Description  
INCREMENT Increment  
DECREMENT Decrement operation  
BACKUP Backup  
Return Value:  
SdkResult.Success success  
SdkResult.Device\_Not\_Ready device is not ready  
SdkResult.Param\_In\_InValid Parameter is not legitimate  
SdkResult.Fail other errors  
3.15 MemoryCard  
MemoryCard class is responsible for managing MemoryCard.  
Get MemoryCard card class objects:  
MemoryCard memoryCard = deviceEngine.getMemoryCardHandler (CardSlotTypeEnum slotType);  
Parameters:  
Parameter Description  
slotTypes Slot enumerated type CardSlotTypeEnum; supports a  
variety of combinations of slots  
CardSlotTypeEnum  
Enumeration Name Description  
ICC1 Default IC card slot  
ICC2 Unavailable  
ICC3 Unavailable  
PSAM1 PSAM slot 1  
PSAM2 PSAM slot 2  
PSAM3 Unavailable  
Confidential 138  
| Attributes | Description |  
| --- | --- |  
| M1CardOperTypeEnum operType | Operation enumeration |  
| byte [] blkData | Data to be operated |  
| int BLKNO | Block number to be operated |  
| int desBlkNo | Destination block number |  
| Enumeration Name | Description |  
| --- | --- |  
| INCREMENT | Increment |  
| DECREMENT | Decrement operation |  
| BACKUP | Backup |  
| Parameter | Description |  
| --- | --- |  
| slotTypes | Slot enumerated type CardSlotTypeEnum; supports a variety of combinations of slots |  
| Enumeration Name | Description |  
| --- | --- |  
| ICC1 | Default IC card slot |  
| ICC2 | Unavailable |  
| ICC3 | Unavailable |  
| PSAM1 | PSAM slot 1 |  
| PSAM2 | PSAM slot 2 |  
| PSAM3 | Unavailable |

## Página 139

SmartPos API Reference Manual: 09/08/21  
RF Non-access card slot  
SWIPE Magnetic stripe card slot  
This module uses the basic flow chart:  
start  
Obtain context  
Obtain Memory context  
Reset  
Call read, write, verfy..operate  
card  
Power off  
3.15.1 reset  
reset  
public int reset(CardTypeEnum cardType);  
Parameters:  
Parameter Description  
cardType Card type  
CardTypeEnum  
Enumeration Name Description  
Confidential 139  
| RF | Non-access card slot |  
| --- | --- |  
| SWIPE | Magnetic stripe card slot |  
| Parameter | Description |  
| --- | --- |  
| cardType | Card type |  
| Enumeration Name | Description |  
| --- | --- |

## Página 140

SmartPos API Reference Manual: 09/08/21  
AT24C01  
AT24C02  
AT24C04  
AT24C08  
AT24C16  
AT24C32  
AT24C64  
AT88SC101  
AT88SC102  
IS23SC1604  
AT88SC153  
AT88SC1608  
SLE4442  
SLE4428  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
3.15.2 read  
read  
public byte[] read(ReadEntity read);  
Parameters:  
Parameter Description  
read ReadEntity  
ReadEntity  
attribute Description  
CardTypeEnum cardType Card type  
Confidential 140  
| AT24C01 | |  
| --- | --- |  
| AT24C02 | |  
| AT24C04 | |  
| AT24C08 | |  
| AT24C16 | |  
| AT24C32 | |  
| AT24C64 | |  
| AT88SC101 | |  
| AT88SC102 | |  
| IS23SC1604 | |  
| AT88SC153 | |  
| AT88SC1608 | |  
| SLE4442 | |  
| SLE4428 | |  
| Parameter | Description |  
| --- | --- |  
| read | ReadEntity |  
| attribute | Description |  
| --- | --- |  
| CardTypeEnum cardType | Card type |

## Página 141

SmartPos API Reference Manual: 09/08/21  
int zone SLE4428 means protection bit mode 0: no protection  
bit, 1: protection bit  
SLE4442 means the storage area, 0: main storage  
area, 1: protection area  
AT88SC153,AT88SC1608 means the partition number  
IS23SC1604 means area code  
int address The starting address, starting at 0  
int readLen Len of read data  
CardTypeEnum  
Enumeration Name Description  
AT24C01  
AT24C02  
AT24C04  
AT24C08  
AT24C16  
AT24C32  
AT24C64  
AT88SC101  
AT88SC102  
IS23SC1604  
AT88SC153  
AT88SC1608  
SLE4442  
SLE4428  
Return Value:  
Success return byte[]  
Fail return null  
3.15.3 write  
write  
public int write(WriteEntity write);  
Parameters:  
Parameter Description  
WriteEntity  
write  
Confidential 141  
| int zone | SLE4428 means protection bit mode 0: no protection bit, 1: protection bit SLE4442 means the storage area, 0: main storage area, 1: protection area AT88SC153,AT88SC1608 means the partition number IS23SC1604 means area code |  
| --- | --- |  
| int address | The starting address, starting at 0 |  
| int readLen | Len of read data |  
| Enumeration Name | Description |  
| --- | --- |  
| AT24C01 | |  
| AT24C02 | |  
| AT24C04 | |  
| AT24C08 | |  
| AT24C16 | |  
| AT24C32 | |  
| AT24C64 | |  
| AT88SC101 | |  
| AT88SC102 | |  
| IS23SC1604 | |  
| AT88SC153 | |  
| AT88SC1608 | |  
| SLE4442 | |  
| SLE4428 | |  
| Parameter | Description |  
| --- | --- |  
| write | WriteEntity |

## Página 142

SmartPos API Reference Manual: 09/08/21  
WriteEntity  
attribute Description  
CardTypeEnum cardType Card type  
int zone SLE4428 means protection bit mode 0: no protection  
bit, 1: protection bit  
SLE4442 means the storage area, 0: main storage  
area, 1: protection area  
AT88SC153,AT88SC1608 means the partition number  
IS23SC1604 means area code  
int address The starting address, starting at 0  
byte[] writeData Write data  
int writeLen Len of write data  
CardTypeEnum  
Enumeration Name Description  
AT24C01  
AT24C02  
AT24C04  
AT24C08  
AT24C16  
AT24C32  
AT24C64  
AT88SC101  
AT88SC102  
IS23SC1604  
AT88SC153  
AT88SC1608  
SLE4442  
SLE4428  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
3.15.4 erase  
erase  
public int earse(EraseEntity erase);  
Confidential 142  
| attribute | Description |  
| --- | --- |  
| CardTypeEnum cardType | Card type |  
| int zone | SLE4428 means protection bit mode 0: no protection bit, 1: protection bit SLE4442 means the storage area, 0: main storage area, 1: protection area AT88SC153,AT88SC1608 means the partition number IS23SC1604 means area code |  
| int address | The starting address, starting at 0 |  
| byte[] writeData | Write data |  
| int writeLen | Len of write data |  
| Enumeration Name | Description |  
| --- | --- |  
| AT24C01 | |  
| AT24C02 | |  
| AT24C04 | |  
| AT24C08 | |  
| AT24C16 | |  
| AT24C32 | |  
| AT24C64 | |  
| AT88SC101 | |  
| AT88SC102 | |  
| IS23SC1604 | |  
| AT88SC153 | |  
| AT88SC1608 | |  
| SLE4442 | |  
| SLE4428 | |

## Página 143

SmartPos API Reference Manual: 09/08/21  
Parameters:  
Parameter Description  
erase EraseEntity  
EraseEntity  
attribute Description  
CardTypeEnum cardType Cardtype ,only support  
IS23SC1604,AT88SC101,AT88SC102  
int address The starting address, starting at 0  
int eraseLen Erase data length, unit byte  
int zone Zone number  
CardTypeEnum  
Enumeration Name Description  
AT24C01  
AT24C02  
AT24C04  
AT24C08  
AT24C16  
AT24C32  
AT24C64  
AT88SC101  
AT88SC102  
IS23SC1604  
AT88SC153  
AT88SC1608  
SLE4442  
SLE4428  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid invaild param  
SdkResult.Fail other errors  
3.15.5 verify  
verify card pin.  
Confidential 143  
| Parameter | Description |  
| --- | --- |  
| erase | EraseEntity |  
| attribute | Description |  
| --- | --- |  
| CardTypeEnum cardType | Cardtype ,only support IS23SC1604,AT88SC101,AT88SC102 |  
| int address | The starting address, starting at 0 |  
| int eraseLen | Erase data length, unit byte |  
| int zone | Zone number |  
| Enumeration Name | Description |  
| --- | --- |  
| AT24C01 | |  
| AT24C02 | |  
| AT24C04 | |  
| AT24C08 | |  
| AT24C16 | |  
| AT24C32 | |  
| AT24C64 | |  
| AT88SC101 | |  
| AT88SC102 | |  
| IS23SC1604 | |  
| AT88SC153 | |  
| AT88SC1608 | |  
| SLE4442 | |  
| SLE4428 | |

## Página 144

SmartPos API Reference Manual: 09/08/21  
public int verify(VerifyEntity verify);  
Parameters:  
Parameter Description  
verify VerifyEntity  
VerifyEntity  
attribute Description  
CardTypeEnum cardType cardtype ,only support  
SLE4428,SLE4442,AT88SC153,AT88SC1608,IS23SC160  
4,AT88SC101,AT88SC102  
byte[] pwd password  
int mode AT88SC153,AT88SC1608 means check mode, 0: read  
check ;1: write check  
AT88SC101 AT88SC102, IS23SC1604 means check  
content 0: security code ;1: erase the password  
int zone AT88SC153,AT88SC1608 means password index  
AT88SC101 AT88SC102, IS23SC1604 means area ,0:  
the whole storage area; 1 ~ n: application code  
CardTypeEnum  
Enumeration Name Description  
AT24C01  
AT24C02  
AT24C04  
AT24C08  
AT24C16  
AT24C32  
AT24C64  
AT88SC101  
AT88SC102  
IS23SC1604  
AT88SC153  
AT88SC1608  
SLE4442  
SLE4428  
Return Value:  
Confidential 144  
| Parameter | Description |  
| --- | --- |  
| verify | VerifyEntity |  
| attribute | Description |  
| --- | --- |  
| CardTypeEnum cardType | cardtype ,only support SLE4428,SLE4442,AT88SC153,AT88SC1608,IS23SC160 4,AT88SC101,AT88SC102 |  
| byte[] pwd | password |  
| int mode | AT88SC153,AT88SC1608 means check mode, 0: read check ;1: write check AT88SC101 AT88SC102, IS23SC1604 means check content 0: security code ;1: erase the password |  
| int zone | AT88SC153,AT88SC1608 means password index AT88SC101 AT88SC102, IS23SC1604 means area ,0: the whole storage area; 1 ~ n: application code |  
| AT88SC153,AT88SC1608 means check mode, 0: read |  
| --- |  
| check ;1: write check |  
| AT88SC101 AT88SC102, IS23SC1604 means check |  
| content 0: security code ;1: erase the password |  
| AT88SC153,AT88SC1608 means password index |  
| --- |  
| AT88SC101 AT88SC102, IS23SC1604 means area ,0: |  
| the whole storage area; 1 ~ n: application code |  
| Enumeration Name | Description |  
| --- | --- |  
| AT24C01 | |  
| AT24C02 | |  
| AT24C04 | |  
| AT24C08 | |  
| AT24C16 | |  
| AT24C32 | |  
| AT24C64 | |  
| AT88SC101 | |  
| AT88SC102 | |  
| IS23SC1604 | |  
| AT88SC153 | |  
| AT88SC1608 | |  
| SLE4442 | |  
| SLE4428 | |

## Página 145

SmartPos API Reference Manual: 09/08/21  
SdkResult.Success the remaining password verification times  
SdkResult.Param\_In\_Invalid invaild param  
SdkResult.Fail other errors  
3.15.6 readEC  
Read remaining password check times.  
public int readEC(ReadECEntity readEC);  
Parameters:  
Parameter Description  
readEC ReadECEntity  
ReadECEntity  
attribute Description  
CardTypeEnum cardType card type , only support  
SLE4428,SLE4442,AT88SC153,AT88SC1608,IS23SC160  
4,AT88SC101,AT88SC102  
int mode AT88SC153,AT88SC1608 means check mode, 0: read  
check ;1: write check  
AT88SC101 AT88SC102, IS23SC1604 means check  
content 0: security code ;1: erase the password  
int zone AT88SC153,AT88SC1608 means password index  
AT88SC101 AT88SC102, IS23SC1604 means area ,0:  
the whole storage area; 1 ~ n: application code  
CardTypeEnum  
Enumeration Name Description  
AT24C01  
AT24C02  
AT24C04  
AT24C08  
AT24C16  
AT24C32  
Confidential 145  
| Parameter | Description |  
| --- | --- |  
| readEC | ReadECEntity |  
| attribute | Description |  
| --- | --- |  
| CardTypeEnum cardType | card type , only support SLE4428,SLE4442,AT88SC153,AT88SC1608,IS23SC160 4,AT88SC101,AT88SC102 |  
| int mode | AT88SC153,AT88SC1608 means check mode, 0: read check ;1: write check AT88SC101 AT88SC102, IS23SC1604 means check content 0: security code ;1: erase the password |  
| int zone | AT88SC153,AT88SC1608 means password index AT88SC101 AT88SC102, IS23SC1604 means area ,0: the whole storage area; 1 ~ n: application code |  
| AT88SC153,AT88SC1608 means check mode, 0: read |  
| --- |  
| check ;1: write check |  
| AT88SC101 AT88SC102, IS23SC1604 means check |  
| content 0: security code ;1: erase the password |  
| AT88SC153,AT88SC1608 means password index |  
| --- |  
| AT88SC101 AT88SC102, IS23SC1604 means area ,0: |  
| the whole storage area; 1 ~ n: application code |  
| Enumeration Name | Description |  
| --- | --- |  
| AT24C01 | |  
| AT24C02 | |  
| AT24C04 | |  
| AT24C08 | |  
| AT24C16 | |  
| AT24C32 | |

## Página 146

SmartPos API Reference Manual: 09/08/21  
AT24C64  
AT88SC101  
AT88SC102  
IS23SC1604  
AT88SC153  
AT88SC1608  
SLE4442  
SLE4428  
Return Value:  
SdkResult.Success the remaining password verification times  
SdkResult.Param\_In\_Invalid invaild param  
SdkResult.Fail other errors  
3.15.7 updateEC  
Modify card password  
public int updateEC(UpdateECEntity updateEC);  
Parameters:  
Parameter Description  
readEC UpdateECEntity  
UpdateECEntity  
attribute Description  
CardTypeEnum cardType Card type, only support  
SLE4428,SLE4442,AT88SC153,AT88SC1608,IS23SC160  
4,AT88SC101,AT88SC102  
byte[] pwd password  
int mode AT88SC153,AT88SC1608 means check mode, 0: read  
check ;1: write check  
AT88SC101 AT88SC102, IS23SC1604 means check  
content 0: security code ;1: erase the password  
int zone AT88SC153,AT88SC1608 means password index  
AT88SC101 AT88SC102, IS23SC1604 means area ,0:  
the whole storage area; 1 ~ n: application code  
Confidential 146  
| AT24C64 | |  
| --- | --- |  
| AT88SC101 | |  
| AT88SC102 | |  
| IS23SC1604 | |  
| AT88SC153 | |  
| AT88SC1608 | |  
| SLE4442 | |  
| SLE4428 | |  
| Parameter | Description |  
| --- | --- |  
| readEC | UpdateECEntity |  
| attribute | Description |  
| --- | --- |  
| CardTypeEnum cardType | Card type, only support SLE4428,SLE4442,AT88SC153,AT88SC1608,IS23SC160 4,AT88SC101,AT88SC102 |  
| byte[] pwd | password |  
| int mode | AT88SC153,AT88SC1608 means check mode, 0: read check ;1: write check AT88SC101 AT88SC102, IS23SC1604 means check content 0: security code ;1: erase the password |  
| int zone | AT88SC153,AT88SC1608 means password index AT88SC101 AT88SC102, IS23SC1604 means area ,0: the whole storage area; 1 ~ n: application code |  
| AT88SC153,AT88SC1608 means check mode, 0: read |  
| --- |  
| check ;1: write check |  
| AT88SC101 AT88SC102, IS23SC1604 means check |  
| content 0: security code ;1: erase the password |  
| AT88SC153,AT88SC1608 means password index |  
| --- |  
| AT88SC101 AT88SC102, IS23SC1604 means area ,0: |  
| the whole storage area; 1 ~ n: application code |

## Página 147

SmartPos API Reference Manual: 09/08/21  
CardTypeEnum  
Enumeration Name Description  
AT24C01  
AT24C02  
AT24C04  
AT24C08  
AT24C16  
AT24C32  
AT24C64  
AT88SC101  
AT88SC102  
IS23SC1604  
AT88SC153  
AT88SC1608  
SLE4442  
SLE4428  
Return Value:  
SdkResult.Success  
SdkResult.Param\_In\_Invalid invaild param  
SdkResult.Fail other errors  
3.15.8 powerOff  
poweroff  
public void powerOff();  
Return Value:  
None  
3.16 Desfire Cards  
DesfireHandler is responsible for interacting with Desfire card.  
Note: Currently, only N5 can support desfire card.  
Get Desfire card handler Object:  
Confidential 147  
| Enumeration Name | Description |  
| --- | --- |  
| AT24C01 | |  
| AT24C02 | |  
| AT24C04 | |  
| AT24C08 | |  
| AT24C16 | |  
| AT24C32 | |  
| AT24C64 | |  
| AT88SC101 | |  
| AT88SC102 | |  
| IS23SC1604 | |  
| AT88SC153 | |  
| AT88SC1608 | |  
| SLE4442 | |  
| SLE4428 | |

## Página 148

SmartPos API Reference Manual: 09/08/21  
DesfireHandler desfireHandler = deviceEngine.getDesfireHandler();  
This module uses the basic flow chart:  
Confidential 148

## Página 149

SmartPos API Reference Manual: 09/08/21  
Confidential 149

## Página 150

SmartPos API Reference Manual: 09/08/21  
3.16.1 Authenticate  
Prototype int authenticate(byte keyNo, byte[] key);  
confirms that both entities (PICC and PCD) can trust each other, DES/3DES algorithm  
Function  
the key no used to authentication process  
Parameters keyNo  
key used for authentication, 16bytes need.  
Key  
if the actual key is only 8bytes long, then should  
extended to 16bytes: key[0...7] || key[0...7].  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
SdkResult.Param\_In\_Invalid Parameter is Invalid  
SdkResult.TimeOut TimeOut  
3.16.2 AuthenticateIso  
int authenticateIso(byte keyNo, byte[] key);  
Prototype  
confirms that both entities (PICC and PCD) can trust each other, DES/3DES /3KDES  
Function  
algorithm  
the key no used to authentication process  
Parameters keyNo  
key used for authentication, 24bytes need.  
Key  
if the actual key is only 8bytes long, then should  
extended to 24bytes: key[0...7] || key[0...7] || key[0...7]  
Confidential 150  
| Prototype | int authenticate(byte keyNo, byte[] key); | None |  
| --- | --- | --- |  
| Function | confirms that both entities (PICC and PCD) can trust each other, DES/3DES algorithm | None |  
| Parameters | keyNo | the key no used to authentication process |  
| None | Key | key used for authentication, 16bytes need. if the actual key is only 8bytes long, then should extended to 16bytes: key[0...7] || key[0...7]. |  
| Return | SdkResult.Success | Success |  
| None | SdkResult.Fail | Fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is Invalid |  
| None | SdkResult.TimeOut | TimeOut |  
| Prototype | int authenticateIso(byte keyNo, byte[] key); | None |  
| --- | --- | --- |  
| Function | confirms that both entities (PICC and PCD) can trust each other, DES/3DES /3KDES algorithm | None |  
| Parameters | keyNo | the key no used to authentication process |  
| None | Key | key used for authentication, 24bytes need. if the actual key is only 8bytes long, then should extended to 24bytes: key[0...7] || key[0...7] || key[0...7] |

## Página 151

SmartPos API Reference Manual: 09/08/21  
if the actual key is only 16bytes long, then should  
extended to 24bytes: key[0...7] || key[8...15] ||  
key[0...7]  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
SdkResult.Param\_In\_Invalid Parameter is Invalid  
SdkResult.TimeOut TimeOut  
3.16.3 AuthenticateAes  
int authenticateAes(byte keyNo, byte[] key);  
Prototype  
confirms that both entities (PICC and PCD) can trust each other, AES algorithm  
Function  
the key no used to authentication process  
Parameters keyNo  
key used for authentication, 16bytes need.  
Key  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
SdkResult.Param\_In\_Invalid Parameter is Invalid  
SdkResult.TimeOut TimeOut  
3.16.4 changeKeySettings  
int changeKeySettings(byte keySettings);  
Prototype  
Changes the master key configuration settings depending on the currently selected  
Function  
AID.  
keySettings ------ for PICC master key:  
Parameters  
bit7~bit4: 0000 RFU  
Confidential 151  
| | | if the actual key is only 16bytes long, then should extended to 24bytes: key[0...7] || key[8...15] || key[0...7] |  
| --- | --- | --- |  
| Return | SdkResult.Success | Success |  
| None | SdkResult.Fail | Fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is Invalid |  
| None | SdkResult.TimeOut | TimeOut |  
| Prototype | int authenticateAes(byte keyNo, byte[] key); | None |  
| --- | --- | --- |  
| Function | confirms that both entities (PICC and PCD) can trust each other, AES algorithm | None |  
| Parameters | keyNo | the key no used to authentication process |  
| None | Key | key used for authentication, 16bytes need. |  
| Return | SdkResult.Success | Success |  
| None | SdkResult.Fail | Fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is Invalid |  
| None | SdkResult.TimeOut | TimeOut |  
| Prototype | int changeKeySettings(byte keySettings); | None |  
| --- | --- | --- |  
| Function | Changes the master key configuration settings depending on the currently selected AID. | None |  
| Parameters | keySettings | ------ for PICC master key: bit7~bit4: 0000 RFU |

## Página 152

SmartPos API Reference Manual: 09/08/21  
bit3: whether a change of the PICC master key settings  
is allowed  
bit2: whether PICC master key authentication is needed  
before Create- / DeleteApplication  
bit1: whether PICC master key authentication is needed  
for application directory access  
bit0: whether the PICC master key is changeable  
------ for Application master key:  
bit7~bit4: hold the Access Rights for changing  
application keys (ChangeKey command)  
0x0: Application master key authentication is  
necessary to change any key (default)  
0x1~0x0D: Authentication with the specified key is  
necessary to change any key.  
0x0E: Authentication with the key to be changed  
(same  
KeyNo) is necessary to change a key.  
0x0F: All Keys (except application master key, see Bit0)  
within this application are frozen.  
bit3: whether a change of the application master key  
settings is allowed  
bit2: whether application master key authentication is  
needed before CreateFile / DeleteFile  
bit1: whether application master key authentication is  
needed for file directory access  
bit0: whether the application master key is  
changeable  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
Confidential 152  
| | | bit3: whether a change of the PICC master key settings is allowed bit2: whether PICC master key authentication is needed before Create- / DeleteApplication bit1: whether PICC master key authentication is needed for application directory access bit0: whether the PICC master key is changeable ------ for Application master key: bit7~bit4: hold the Access Rights for changing application keys (ChangeKey command) 0x0: Application master key authentication is necessary to change any key (default) 0x1~0x0D: Authentication with the specified key is necessary to change any key. 0x0E: Authentication with the key to be changed (same KeyNo) is necessary to change a key. 0x0F: All Keys (except application master key, see Bit0) within this application are frozen. bit3: whether a change of the application master key settings is allowed bit2: whether application master key authentication is needed before CreateFile / DeleteFile bit1: whether application master key authentication is needed for file directory access bit0: whether the application master key is changeable |  
| --- | --- | --- |  
| Return | SdkResult.Success | Success |  
| None | SdkResult.Fail | Fail |

## Página 153

SmartPos API Reference Manual: 09/08/21  
SdkResult.Param\_In\_Invalid Parameter is Invalid  
SdkResult.TimeOut TimeOut  
3.16.5 getKeySettings  
KeySettingsEntity getKeySettings();  
Prototype  
get configuration information on the PICC and application master key configuration  
Function  
settings, and get maximum number of keys which can be stored within the selected  
application.  
Depending on the master key settings, a preceding authentication with the master  
key is required.  
null  
Parameters  
KeySettingsEntity getKeySettings  
Return  
current master key setting  
getMaxKeyNum  
maximum number of keys which can be stored within the  
selected application  
3.16.6 changePiccMasterkey  
int changePiccMasterkey(KeyTypeEnum masterKeyType, byte[] key, byte aesVersion);  
Prototype  
change PICC master key  
Function  
1. according to PICC master key setting, a authentication with PICC master key is  
necessary  
KeyTypeEnum  
Enumeration Name Description  
Confidential 153  
| | SdkResult.Param\_In\_Invalid | Parameter is Invalid |  
| --- | --- | --- |  
| None | SdkResult.TimeOut | TimeOut |  
| Prototype | KeySettingsEntity getKeySettings(); | None |  
| --- | --- | --- |  
| Function | get configuration information on the PICC and application master key configuration settings, and get maximum number of keys which can be stored within the selected application. Depending on the master key settings, a preceding authentication with the master key is required. | None |  
| Parameters | null | |  
| Return | KeySettingsEntity | getKeySettings current master key setting |  
| None | None | getMaxKeyNum maximum number of keys which can be stored within the selected application |  
| Prototype | int changePiccMasterkey(KeyTypeEnum masterKeyType, byte[] key, byte aesVersion); |  
| --- | --- |  
| Function | change PICC master key 1. according to PICC master key setting, a authentication with PICC master key is necessary |  
| Enumeration Name | Description |  
| --- | --- |

## Página 154

SmartPos API Reference Manual: 09/08/21  
2. After a successful change of the key used to reach the current authentication  
status, this authentication is invalidated i.e. an authentication with the new key is  
necessary for subsequent operations.  
masterKeyType PICC master key type  
Parameters  
key key information (16/24 bytes)  
aesVersion key version, only valid when type = {@link  
KeyTypeEnum#AES}.  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
SdkResult.Param\_In\_Invalid Parameter is Invalid  
SdkResult.TimeOut TimeOut  
DES\_TDES\_128 16bytes DES/3DES key  
KEYTYPE\_B 24bytes 3KDES key  
AES 16bytes AES key  
3.16.7 changeAppKey  
int changeAppKey(KeyTypeEnum appKeyType, byte keyNo, byte[] oldKey, byte[]  
Prototype  
newKey, byte aesVersion);  
change application master key  
Function  
1. according to application master key setting, a authentication with specified key is  
necessary  
2. After a successful change of the key used to reach the current authentication  
status, this authentication is invalidated i.e. an authentication with the new key is  
necessary for subsequent operations.  
appKeyType app key type{ KeyTypeEnum}  
Parameters  
keyNo the key to change  
oldKey old key value  
Confidential 154  
| | 2. After a successful change of the key used to reach the current authentication status, this authentication is invalidated i.e. an authentication with the new key is necessary for subsequent operations. | None | None |  
| --- | --- | --- | --- |  
| Parameters | masterKeyType | None | PICC master key type |  
| None | key | None | key information (16/24 bytes) |  
| None | aesVersion | None | key version, only valid when type = {@link KeyTypeEnum#AES}. |  
| Return | SdkResult.Success | None | Success |  
| None | SdkResult.Fail | None | Fail |  
| None | SdkResult.Param\_In\_Invalid | None | Parameter is Invalid |  
| None | SdkResult.TimeOut | None | TimeOut |  
| DES\_TDES\_128 | None | 16bytes DES/3DES key | None |  
| KEYTYPE\_B | None | 24bytes 3KDES key | None |  
| AES | None | 16bytes AES key | None |  
| Prototype | int changeAppKey(KeyTypeEnum appKeyType, byte keyNo, byte[] oldKey, byte[] newKey, byte aesVersion); | None |  
| --- | --- | --- |  
| Function | change application master key 1. according to application master key setting, a authentication with specified key is necessary 2. After a successful change of the key used to reach the current authentication status, this authentication is invalidated i.e. an authentication with the new key is necessary for subsequent operations. | None |  
| Parameters | appKeyType | app key type{ KeyTypeEnum} |  
| None | keyNo | the key to change |  
| None | oldKey | old key value |

## Página 155

SmartPos API Reference Manual: 09/08/21  
newKey new key value  
aesVersion key version, only valid when type = {@link  
KeyTypeEnum#AES}.  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
SdkResult.Param\_In\_Invalid Parameter is Invalid  
SdkResult.TimeOut TimeOut  
3.16.8 getKeyVersion  
byte getKeyVersion(byte keyNo);  
Prototype  
read out the current key version of any key stored on the PICC  
Function  
This command can be issued without valid authentication.  
keyNo key no  
Parameters  
key version of this key  
Return  
3.16.9 createApplication  
int createApplication(ApplicationEntity application);  
Prototype  
create new applications on the PICC.  
Function  
1. Application Identifier 0x00 00 00 is reserved as a reference to the PICC itself.  
2. After application be created, All keys are initialised with a string consisting of  
0x00 bytes  
3. Before any setup of a file system, it is recommemded to configure the whole  
card using the command ‘SetConfiguration’. This command will initialize all keys of  
any created application to a specified value which is taken out of the default key  
ApplicationEntity  
Confidential 155  
| | newKey | new key value |  
| --- | --- | --- |  
| None | aesVersion | key version, only valid when type = {@link KeyTypeEnum#AES}. |  
| Return | SdkResult.Success | Success |  
| None | SdkResult.Fail | Fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is Invalid |  
| None | SdkResult.TimeOut | TimeOut |  
| Prototype | byte getKeyVersion(byte keyNo); | None |  
| --- | --- | --- |  
| Function | read out the current key version of any key stored on the PICC This command can be issued without valid authentication. | None |  
| Parameters | keyNo | key no |  
| Return | key version of this key | None |  
| Prototype | int createApplication(ApplicationEntity application); |  
| --- | --- |  
| Function | create new applications on the PICC. 1. Application Identifier 0x00 00 00 is reserved as a reference to the PICC itself. 2. After application be created, All keys are initialised with a string consisting of 0x00 bytes 3. Before any setup of a file system, it is recommemded to configure the whole card using the command ‘SetConfiguration’. This command will initialize all keys of any created application to a specified value which is taken out of the default key |

## Página 156

SmartPos API Reference Manual: 09/08/21  
and default version from the ‘SetConfiguration’ command. Without this command  
all keys are consisting of 0x00 bytes.  
application {@link ApplicationEntity}  
Parameters  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
SdkResult.Param\_In\_Invalid Parameter is Invalid  
SdkResult.TimeOut TimeOut  
Attributes Description  
byte[] aid application identifier  
byte[] isoFid 2 byte ISO/IEC 7816-4 File Identifies for files within the  
application  
byte[]dfName "DF-name" used in 7816-4 mode in combination with the ISO  
SELECT command  
Byte masterKeySetting Application master key setting  
byte numberOfKey Number of keys that can be stored within the application for  
cryptographic purposes. A maximum of 14 keys can be stored  
within an application of MIFARE DESFire EV1. One can also create  
an application with no keys  
KeyTypeEnum keyType the key type of application  
boolean isSupFid whether use File Identifies  
boolean isSupIsoFid whether use of 2 byte ISO/IEC 7816-4 File Identifies for files  
within the Application  
3.16.10 deleteApplication  
int deleteApplication(byte[] aid);  
Prototype  
allows to permanently deactivate applications on the PICC  
Function  
Confidential 156  
| | and default version from the ‘SetConfiguration’ command. Without this command all keys are consisting of 0x00 bytes. | None | None |  
| --- | --- | --- | --- |  
| Parameters | application | None | {@link ApplicationEntity} |  
| Return | SdkResult.Success | None | Success |  
| None | SdkResult.Fail | None | Fail |  
| None | SdkResult.Param\_In\_Invalid | None | Parameter is Invalid |  
| None | SdkResult.TimeOut | None | TimeOut |  
| Attributes | None | Description | None |  
| byte[] aid | None | application identifier | None |  
| byte[] isoFid | None | 2 byte ISO/IEC 7816-4 File Identifies for files within the application | None |  
| byte[]dfName | None | "DF-name" used in 7816-4 mode in combination with the ISO SELECT command | None |  
| Byte masterKeySetting | None | Application master key setting | None |  
| byte numberOfKey | None | Number of keys that can be stored within the application for cryptographic purposes. A maximum of 14 keys can be stored within an application of MIFARE DESFire EV1. One can also create an application with no keys | None |  
| KeyTypeEnum keyType | None | the key type of application | None |  
| boolean isSupFid | None | whether use File Identifies | None |  
| boolean isSupIsoFid | None | whether use of 2 byte ISO/IEC 7816-4 File Identifies for files within the Application | None |  
| Prototype | int deleteApplication(byte[] aid); |  
| --- | --- |  
| Function | allows to permanently deactivate applications on the PICC |

## Página 157

SmartPos API Reference Manual: 09/08/21  
1. Depending on the PICC master key settings, an PICC master key authentication is  
required.  
2. The AID allocation is removed, therefore it is possible to create a new  
application with the deleted application's AID. However, the deleted memory  
blocks can only be recovered by using the FormatPICC command which erases the  
full user memory of the PICC.  
aid application Identifies (3bytes)  
Parameters  
SdkResult.Success success  
Return  
SdkResult.Fail fail  
SdkResult.Param\_In\_Invalid  
Parameter is not legitimate  
SdkResult.TimeOut timeout  
3.16.11 getAids  
List getAids();  
Prototype  
returns the Application IDentifiers of all active applications on a PICC.  
Function  
Depending on the PICC master key settings a successful authentication with the  
PICC master key might be required to execute this command.  
null  
Parameters  
if error return empty list.  
Return  
3.16.12 getDfNames  
List getDfNames();  
Prototype  
Returns the ISO/IEC 7816-4 DF-Names of all active applications on a PICC  
Function  
Depending on the PICC master key settings a successful authentication with the  
PICC master key might be required to execute this command.  
Confidential 157  
| | 1. Depending on the PICC master key settings, an PICC master key authentication is required. 2. The AID allocation is removed, therefore it is possible to create a new application with the deleted application's AID. However, the deleted memory blocks can only be recovered by using the FormatPICC command which erases the full user memory of the PICC. | None |  
| --- | --- | --- |  
| Parameters | aid | application Identifies (3bytes) |  
| Return | SdkResult.Success | success |  
| None | SdkResult.Fail | fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is not legitimate |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | List getAids(); | None |  
| --- | --- | --- |  
| Function | returns the Application IDentifiers of all active applications on a PICC. Depending on the PICC master key settings a successful authentication with the PICC master key might be required to execute this command. | None |  
| Parameters | null | |  
| Return | if error return empty list. | None |  
| Prototype | List getDfNames(); |  
| --- | --- |  
| Function | Returns the ISO/IEC 7816-4 DF-Names of all active applications on a PICC Depending on the PICC master key settings a successful authentication with the PICC master key might be required to execute this command. |

## Página 158

SmartPos API Reference Manual: 09/08/21  
null  
Parameters  
if error return empty list.  
Return  
3.16.13 selectApplication  
int selectApplication(byte[] aid);  
Prototype  
select one specific application for further access.  
Function  
1. each SelectApplication command invalidates the current authentication status.  
2. If this pAID is 0x00 00 00, the PICC level is selected and any further operations  
(typically commands like CreateApplication, DeleteApplication) are related to this  
level.  
3. If an application with the specified AID is found in the application directory of  
the PICC, the subsequent commands interact with this application.  
aid Application Identifier (3bytes)  
Parameters  
SdkResult.Success success  
Return  
SdkResult.Fail fail  
SdkResult.Param\_In\_Invalid Parameter is not legitimate  
SdkResult.TimeOut timeout  
3.16.14 formatPicc  
int formatPicc();  
Prototype  
This command releases the PICC user memory.  
Function  
1. The FormatPICC Command releases all allocated user memory on the PICC.  
2. All applications are deleted and all files within those applications are deleted.  
Confidential 158  
| Parameters | null | |  
| --- | --- | --- |  
| Return | if error return empty list. | None |  
| Prototype | int selectApplication(byte[] aid); | None |  
| --- | --- | --- |  
| Function | select one specific application for further access. 1. each SelectApplication command invalidates the current authentication status. 2. If this pAID is 0x00 00 00, the PICC level is selected and any further operations (typically commands like CreateApplication, DeleteApplication) are related to this level. 3. If an application with the specified AID is found in the application directory of the PICC, the subsequent commands interact with this application. | None |  
| Parameters | aid | Application Identifier (3bytes) |  
| Return | SdkResult.Success | success |  
| None | SdkResult.Fail | fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is not legitimate |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | int formatPicc(); |  
| --- | --- |  
| Function | This command releases the PICC user memory. 1. The FormatPICC Command releases all allocated user memory on the PICC. 2. All applications are deleted and all files within those applications are deleted. |

## Página 159

SmartPos API Reference Manual: 09/08/21  
3. This command always requires a preceding authentication with the PICC  
master key.  
4. The PICC master key and the PICC master key settings keep their currently set  
values, they are not influenced by this command.  
null  
Parameters  
SdkResult.Success success  
Return  
SdkResult.Fail fail  
SdkResult.Param\_In\_Invalid Parameter is not legitimate  
SdkResult.TimeOut timeout  
3.16.15 getVersion  
VersionEntity getVersion();  
Prototype  
Return manufacturing related data of the PICC.  
Function  
null  
Parameters  
VersionEntity {@link VersionEntity }  
Return  
VersionEntity  
Attributes Description  
byte hwVendorId codes the vendor ID ( 0x04 for PHILIPS )  
byte hwType codes the type (here 0x01 )  
byte hwSubType codes the subtype (here 0x01 )  
byte hwMajorVer codes the major version number  
byte hwMinorVer codes the minor version number  
byte hwSize codes the storage size (here 0x1A = 8192 bytes )  
Confidential 159  
| | 3. This command always requires a preceding authentication with the PICC master key. 4. The PICC master key and the PICC master key settings keep their currently set values, they are not influenced by this command. | None |  
| --- | --- | --- |  
| Parameters | null | |  
| Return | SdkResult.Success | success |  
| None | SdkResult.Fail | fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is not legitimate |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | VersionEntity getVersion(); | None |  
| --- | --- | --- |  
| Function | Return manufacturing related data of the PICC. | None |  
| Parameters | null | |  
| Return | VersionEntity | {@link VersionEntity } |  
| Attributes | Description |  
| --- | --- |  
| byte hwVendorId | codes the vendor ID ( 0x04 for PHILIPS ) |  
| byte hwType | codes the type (here 0x01 ) |  
| byte hwSubType | codes the subtype (here 0x01 ) |  
| byte hwMajorVer | codes the major version number |  
| byte hwMinorVer | codes the minor version number |  
| byte hwSize | codes the storage size (here 0x1A = 8192 bytes ) |

## Página 160

SmartPos API Reference Manual: 09/08/21  
byte hwProtocol codes the communication protocol type (here 0x05 meaning ISO  
14443-2 and -3 )  
byte swVendorId codes the vendor ID ( here 0x04 for PHILIPS )  
byte swType codes the type ( here 0x01 )  
byte swSubType codes the subtype ( here 0x01 )  
byte swMajorVer codes the major version  
byte swMinorVer codes the minor version  
byte swSize codes the storage size (here 0x1A = 8192 bytes )  
byte swProtocol codes the communication protocol type (here 0x05 meaning ISO  
14443-3 and -4 )  
byte[] uid code the unique serial number  
byte[] batchNo code the production batch number  
byte weekOfProduction codes the calendar week of production  
byte yearOfProduction codes the year of production  
3.16.16 getFreeMemory  
int getFreeMemory();  
Prototype  
Returns the available bytes on the PICC  
Function  
null  
Parameters  
SdkResult.Success success  
Return  
SdkResult.Fail fail  
SdkResult.Param\_In\_Invalid Parameter is not legitimate  
SdkResult.TimeOut timeout  
Confidential 160  
| byte hwProtocol | codes the communication protocol type (here 0x05 meaning ISO 14443-2 and -3 ) |  
| --- | --- |  
| byte swVendorId | codes the vendor ID ( here 0x04 for PHILIPS ) |  
| byte swType | codes the type ( here 0x01 ) |  
| byte swSubType | codes the subtype ( here 0x01 ) |  
| byte swMajorVer | codes the major version |  
| byte swMinorVer | codes the minor version |  
| byte swSize | codes the storage size (here 0x1A = 8192 bytes ) |  
| byte swProtocol | codes the communication protocol type (here 0x05 meaning ISO 14443-3 and -4 ) |  
| byte[] uid | code the unique serial number |  
| byte[] batchNo | code the production batch number |  
| byte weekOfProduction | codes the calendar week of production |  
| byte yearOfProduction | codes the year of production |  
| Prototype | int getFreeMemory(); | None |  
| --- | --- | --- |  
| Function | Returns the available bytes on the PICC | None |  
| Parameters | null | |  
| Return | SdkResult.Success | success |  
| None | SdkResult.Fail | fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is not legitimate |  
| None | SdkResult.TimeOut | timeout |

## Página 161

SmartPos API Reference Manual: 09/08/21  
3.16.17 setConfiguration  
int setConfiguration(byte option, byte[] info);  
Prototype  
set PICC configuration  
Function  
PICC master key authentication on card level needs to be performed prior to this  
command.  
option configuration type, value as following  
Parameters  
0x00: info data is the configuration byte  
0x01: info data is the default key version and default  
key all applications will be personalized during  
creation with this default key and version  
instead of 0x00  
0x02: info data is the user defined ATS  
0xxx: RF  
info configuration information, according to option  
if option = 0x00, the configuration byte showed as  
following:  
bit0 = 0 Format card enabled  
bit0 = 1 Format card disabled;can not be reset  
bit1 = 0 Random ID disabled  
bit1 = 1 Random ID enabled; can not be reset  
if option = 0x01, the \*info should be 24bytes key and  
1byte default version  
SdkResult.Success success  
Return  
SdkResult.Fail fail  
SdkResult.Param\_In\_Invalid Parameter is not legitimate  
SdkResult.TimeOut timeout  
Confidential 161  
| Prototype | int setConfiguration(byte option, byte[] info); | None |  
| --- | --- | --- |  
| Function | set PICC configuration PICC master key authentication on card level needs to be performed prior to this command. | None |  
| Parameters | option | configuration type, value as following 0x00: info data is the configuration byte 0x01: info data is the default key version and default key all applications will be personalized during creation with this default key and version instead of 0x00 0x02: info data is the user defined ATS 0xxx: RF |  
| None | info | configuration information, according to option if option = 0x00, the configuration byte showed as following: bit0 = 0 Format card enabled bit0 = 1 Format card disabled;can not be reset bit1 = 0 Random ID disabled bit1 = 1 Random ID enabled; can not be reset if option = 0x01, the \*info should be 24bytes key and 1byte default version |  
| Return | SdkResult.Success | success |  
| None | SdkResult.Fail | fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is not legitimate |  
| None | SdkResult.TimeOut | timeout |

## Página 162

SmartPos API Reference Manual: 09/08/21  
3.16.18 getCardUid  
byte[] getCardUid();  
Prototype  
return the uid of PICC  
Function  
1. An authentication with any key needs to be performed prior to this command  
2. This command is only available when {@link  
DesfireHandler#authenticateIso(byte, byte[])} or {@link  
DesfireHandler#authenticateAes(byte, byte[])} called  
null  
Parameters  
uid information of PICC (7bytes)  
Return  
3.16.19 getFids  
List getFids();  
Prototype  
returns the File IDentifiers of all active files within the currently selected  
Function  
application.  
1. Depending on the application master key settings, a preceding authentication  
with the application master key might be required.  
2. Each File ID is coded in one byte and is in the range from 0x00 to 0x1F.  
null  
Parameters  
if error return empty list.  
Return  
3.16.20 getIsoFids  
List getIsoFids();  
Prototype  
Confidential 162  
| Prototype | byte[] getCardUid(); | None |  
| --- | --- | --- |  
| Function | return the uid of PICC 1. An authentication with any key needs to be performed prior to this command 2. This command is only available when {@link DesfireHandler#authenticateIso(byte, byte[])} or {@link DesfireHandler#authenticateAes(byte, byte[])} called | None |  
| Parameters | null | |  
| Return | uid information of PICC (7bytes) | None |  
| Prototype | List getFids(); | None |  
| --- | --- | --- |  
| Function | returns the File IDentifiers of all active files within the currently selected application. 1. Depending on the application master key settings, a preceding authentication with the application master key might be required. 2. Each File ID is coded in one byte and is in the range from 0x00 to 0x1F. | None |  
| Parameters | null | |  
| Return | if error return empty list. | None |  
| Prototype | List getIsoFids(); |  
| --- | --- |

## Página 163

SmartPos API Reference Manual: 09/08/21  
Returns the 2 byte ISO/IEC 7816-4 File IDentifiers of all active files within the  
Function  
currently selected application  
1. Depending on the application master key settings, a preceding authentication  
with the application master key might be required.  
2. Each ISO File ID is coded in two byte .  
null  
Parameters  
if error return empty list.  
Return  
3.16.21 getFileSettings  
FileSettingsEntity getFileSettings(byte fileNo);  
Prototype  
get information on the properties of a specific file.  
Function  
1. This file number must be in the range between 0x00 and 0x1F.  
2. Depending on the application master key settings, a preceding authentication  
with the application master key might be required.  
3. After updating a value file's value but before issuing the CommitTransaction  
command, the GetFileSettings command will always retrieve the old, unchanged  
limit for the limited credit value.  
fileNo the specific file no, value 0~0x1F allowed  
Parameters  
FileSettingsEntity  
Return  
FileSettingsEntity  
Attributes Description  
byte fileType DESfire file type:  
0x00 --- Standard Data Files  
0x01 --- Backup Data Files  
0x02 --- Value Files wih Backup  
0x03 --- Linear record Files with Backup  
0x04 --- Cyclic Record Files with Backup  
Confidential 163  
| Function | Returns the 2 byte ISO/IEC 7816-4 File IDentifiers of all active files within the currently selected application 1. Depending on the application master key settings, a preceding authentication with the application master key might be required. 2. Each ISO File ID is coded in two byte . | None |  
| --- | --- | --- |  
| Parameters | null | |  
| Return | if error return empty list. | None |  
| Prototype | FileSettingsEntity getFileSettings(byte fileNo); | None |  
| --- | --- | --- |  
| Function | get information on the properties of a specific file. 1. This file number must be in the range between 0x00 and 0x1F. 2. Depending on the application master key settings, a preceding authentication with the application master key might be required. 3. After updating a value file's value but before issuing the CommitTransaction command, the GetFileSettings command will always retrieve the old, unchanged limit for the limited credit value. | None |  
| Parameters | fileNo | the specific file no, value 0~0x1F allowed |  
| Return | FileSettingsEntity | None |  
| Attributes | Description |  
| --- | --- |  
| byte fileType | DESfire file type: 0x00 --- Standard Data Files 0x01 --- Backup Data Files 0x02 --- Value Files wih Backup 0x03 --- Linear record Files with Backup 0x04 --- Cyclic Record Files with Backup |

## Página 164

SmartPos API Reference Manual: 09/08/21  
byte commSettings 0x00 or 0x02 --- Plain communication  
0x01 --- Plain communication secured by MACing  
0x03 --- Fully enciphered communication  
byte readAccessRightKeyNum Access right capability, 0x0E means free access, and  
0x0F means deny access. the reference number of the  
key which needs to be authenticated prior to Read  
Access and Read&Write Access  
byte writeAccessRightKeyNum the reference number of the key which needs to be  
authentication prior to Write Access and Read&Write  
Access  
Byte the reference number of the key which needs to be  
readAndWriteAccessRightKeyNum authentication prior to Read&Write Access  
byte changeAccessRightKeyNum the reference number of the key,which is necessary to  
be authenticated with in order to change the access  
rights for the file and to link each access right to key  
numbers  
int fileSize the user file size in bytes, only available when  
file\_type = 0x00 or file\_type = 0x01  
int lowerLimit lower limit of the value file ,only available when  
file\_type = 0x02  
int upperLimit upper limit of the value file,only available when  
file\_type = 0x02  
the current maximum" limited limitedCreditValue,only available when file\_type =  
credit" value 0x02  
boolean limitedCreditEnabled if the LimitedCredit command is allowed for this  
file,only available when file\_type = 0x03 or file\_type =  
0x04  
int recordSize the size of one single record (as deefined at file  
creation),only available when file\_type = 0x03 or  
file\_type = 0x04  
int maxNumberOfRecords the maximum number of records within the record  
file (as defined at file creation),only available when  
file\_type = 0x03 or file\_type = 0x04  
int currentNumberOfRecords the current number of records within the record  
file,only available when file\_type = 0x03 or file\_type =  
0x04  
3.16.22 changeFileSettings  
Confidential 164  
| byte commSettings | 0x00 or 0x02 --- Plain communication 0x01 --- Plain communication secured by MACing 0x03 --- Fully enciphered communication |  
| --- | --- |  
| byte readAccessRightKeyNum | Access right capability, 0x0E means free access, and 0x0F means deny access. the reference number of the key which needs to be authenticated prior to Read Access and Read&Write Access |  
| byte writeAccessRightKeyNum | the reference number of the key which needs to be authentication prior to Write Access and Read&Write Access |  
| Byte readAndWriteAccessRightKeyNum | the reference number of the key which needs to be authentication prior to Read&Write Access |  
| byte changeAccessRightKeyNum | the reference number of the key,which is necessary to be authenticated with in order to change the access rights for the file and to link each access right to key numbers |  
| int fileSize | the user file size in bytes, only available when file\_type = 0x00 or file\_type = 0x01 |  
| int lowerLimit | lower limit of the value file ,only available when file\_type = 0x02 |  
| int upperLimit | upper limit of the value file,only available when file\_type = 0x02 |  
| the current maximum" limited credit" value | limitedCreditValue,only available when file\_type = 0x02 |  
| boolean limitedCreditEnabled | if the LimitedCredit command is allowed for this file,only available when file\_type = 0x03 or file\_type = 0x04 |  
| int recordSize | the size of one single record (as deefined at file creation),only available when file\_type = 0x03 or file\_type = 0x04 |  
| int maxNumberOfRecords | the maximum number of records within the record file (as defined at file creation),only available when file\_type = 0x03 or file\_type = 0x04 |  
| int currentNumberOfRecords | the current number of records within the record file,only available when file\_type = 0x03 or file\_type = 0x04 |

## Página 165

SmartPos API Reference Manual: 09/08/21  
int changeFileSettings(byte fileNo, byte commSettings, byte  
Prototype  
newReadAccessKeyNum, byte newWriteAccessKeyNum, byte  
newReadAndWriteAccessKeyNum, byte newChangeAccessKeyNum);  
changes the access parameters of an existing file  
Function  
1. This change only succeeds if the current "Change Access Right" is different from  
"never", that is old\_change\_access\_keyno != 0x0E  
2. To guarantee that the ChangeFileSettings command is coming from the same  
party which did the preceding authentication, it is necessary to apply basically the  
same security mechanism as used with the ChangeKey command  
fileNo the specific file no, value 0~0x1F allowed  
Parameters  
commSettings 0x00 or 0x02 --- Plain communication  
0x01 --- Plain communication secured by  
MACing  
0x03--- Fully enciphered communication  
newReadAccessKeyNum new Read Access Right Key No  
newWriteAccessKeyNum new Write Access Right Key No  
newReadAndWriteAccessKeyNum new Read and Write Access Right Key No  
newChangeAccessKeyNum new Change Access Right Key No  
SdkResult.Success success  
Return  
SdkResult.Fail fail  
SdkResult.Param\_In\_Invalid Parameter is not legitimate  
SdkResult.TimeOut timeout  
3.16.23 createStdDataFile  
int createStdDataFile(byte fileNo, DataFileEntity dataFile);  
Prototype  
Confidential 165  
| Prototype | int changeFileSettings(byte fileNo, byte commSettings, byte newReadAccessKeyNum, byte newWriteAccessKeyNum, byte newReadAndWriteAccessKeyNum, byte newChangeAccessKeyNum); | None |  
| --- | --- | --- |  
| Function | changes the access parameters of an existing file 1. This change only succeeds if the current "Change Access Right" is different from "never", that is old\_change\_access\_keyno != 0x0E 2. To guarantee that the ChangeFileSettings command is coming from the same party which did the preceding authentication, it is necessary to apply basically the same security mechanism as used with the ChangeKey command | None |  
| Parameters | fileNo | the specific file no, value 0~0x1F allowed |  
| None | commSettings | 0x00 or 0x02 --- Plain communication 0x01 --- Plain communication secured by MACing 0x03--- Fully enciphered communication |  
| None | newReadAccessKeyNum | new Read Access Right Key No |  
| None | newWriteAccessKeyNum | new Write Access Right Key No |  
| None | newReadAndWriteAccessKeyNum | new Read and Write Access Right Key No |  
| None | newChangeAccessKeyNum | new Change Access Right Key No |  
| Return | SdkResult.Success | success |  
| None | SdkResult.Fail | fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is not legitimate |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | int createStdDataFile(byte fileNo, DataFileEntity dataFile); |  
| --- | --- |

## Página 166

SmartPos API Reference Manual: 09/08/21  
create files for the storage of plain unformatted user data within an existing  
Function  
application on the PICC  
fileNo the specific file no, value 0~0x1F allowed  
Parameters  
dataFile file settings {@link DataFileEntity}  
SdkResult.Success success  
Return  
SdkResult.Fail fail  
SdkResult.Param\_In\_Invalid Parameter is not legitimate  
SdkResult.TimeOut timeout  
DataFileEntity  
Attributes Description  
boolean isoFidEnable whether ISO/IEC 7816-4 File IDentifiers enabled (0x00  
- disabled, 0x01-enabled)  
byte[] isoFid 2bytes ISO/IEC 7816-4 File IDentifiers  
commSettings 0x00 or 0x02 --- Plain communication  
0x01 --- Plain communication secured by MACing  
0x03--- Fully enciphered communication  
byte readAccessRightKeyNum Access right capability, 0x0E means free access, and  
0x0F means deny access. the reference number of the  
key which needs to be authenticated prior to Read  
Access and Read&Write Access  
byte writeAccessRightKeyNum the reference number of the key which needs to be  
authentication prior to Write Access and Read&Write  
Access  
Byte the reference number of the key which needs to be  
readAndWriteAccessRightKeyNum authentication prior to Read&Write Access  
byte changeAccessRightKeyNum the reference number of the key,which is necessary to  
be authenticated with in order to change the access  
Confidential 166  
| Function | create files for the storage of plain unformatted user data within an existing application on the PICC | None |  
| --- | --- | --- |  
| Parameters | fileNo | the specific file no, value 0~0x1F allowed |  
| None | dataFile | file settings {@link DataFileEntity} |  
| Return | SdkResult.Success | success |  
| None | SdkResult.Fail | fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is not legitimate |  
| None | SdkResult.TimeOut | timeout |  
| Attributes | Description |  
| --- | --- |  
| boolean isoFidEnable | whether ISO/IEC 7816-4 File IDentifiers enabled (0x00 - disabled, 0x01-enabled) |  
| byte[] isoFid | 2bytes ISO/IEC 7816-4 File IDentifiers |  
| commSettings | 0x00 or 0x02 --- Plain communication 0x01 --- Plain communication secured by MACing 0x03--- Fully enciphered communication |  
| byte readAccessRightKeyNum | Access right capability, 0x0E means free access, and 0x0F means deny access. the reference number of the key which needs to be authenticated prior to Read Access and Read&Write Access |  
| byte writeAccessRightKeyNum | the reference number of the key which needs to be authentication prior to Write Access and Read&Write Access |  
| Byte readAndWriteAccessRightKeyNum | the reference number of the key which needs to be authentication prior to Read&Write Access |  
| byte changeAccessRightKeyNum | the reference number of the key,which is necessary to be authenticated with in order to change the access |

## Página 167

SmartPos API Reference Manual: 09/08/21  
rights for the file and to link each access right to key  
numbers  
int fileSize the user file size in bytes, only available when  
file\_type = 0x00 or file\_type = 0x01  
3.16.24 createBackupDatafile  
int createBackupDatafile(byte fileNo, DataFileEntity dataFile);  
Prototype  
create files for the storage of plain unformatted user data within an existing  
Function  
application on the PICC, additionally supporting the feature of an integrated  
backup mechanism  
1. Due to the mirror image a BackupDataFile always consumes DOUBLE the NV-  
memory on the PICC compared to a StdDataFile with the same specified FileSize.  
2. Every Write command is done in a independent mirror image of this file. To  
validate a write access to this file type, it is necessary to confirm it with a  
CommitTransaction command. If no CommitTransaction command is send by the  
PCD, only the mirror image is changed, the original data remains unchanged and  
valid.  
fileNo the specific file no, value 0~0x1F allowed  
Parameters  
dataFile file settings {@link DataFileEntity}  
SdkResult.Success success  
Return  
SdkResult.Fail fail  
SdkResult.Param\_In\_Invalid Parameter is not legitimate  
SdkResult.TimeOut timeout  
3.16.25 createValueFile  
int createValueFile(byte fileNo, ValueFileEntity valueFile);  
Prototype  
Confidential 167  
| | rights for the file and to link each access right to key numbers |  
| --- | --- |  
| int fileSize | the user file size in bytes, only available when file\_type = 0x00 or file\_type = 0x01 |  
| Prototype | int createBackupDatafile(byte fileNo, DataFileEntity dataFile); | None |  
| --- | --- | --- |  
| Function | create files for the storage of plain unformatted user data within an existing application on the PICC, additionally supporting the feature of an integrated backup mechanism 1. Due to the mirror image a BackupDataFile always consumes DOUBLE the NV- memory on the PICC compared to a StdDataFile with the same specified FileSize. 2. Every Write command is done in a independent mirror image of this file. To validate a write access to this file type, it is necessary to confirm it with a CommitTransaction command. If no CommitTransaction command is send by the PCD, only the mirror image is changed, the original data remains unchanged and valid. | None |  
| Parameters | fileNo | the specific file no, value 0~0x1F allowed |  
| None | dataFile | file settings {@link DataFileEntity} |  
| Return | SdkResult.Success | success |  
| None | SdkResult.Fail | fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is not legitimate |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | int createValueFile(byte fileNo, ValueFileEntity valueFile); |  
| --- | --- |

## Página 168

SmartPos API Reference Manual: 09/08/21  
create files for the storage and manipulation of 32bit signed integer values within  
Function  
an existing application on the PICC  
ValueFiles feature always the integrated backup mechanism. Therefore every  
access changing the value needs to be validated using the CommitTransaction  
command  
1. It is necessary to validate the updated value with a CommitTransaction  
command. An AbortTransaction command will invalidate all changes  
2. The value modifications of Credit, Debit and LimitedCredit commands are  
cumulated until a CommitTransaction command is issued.  
fileNo the specific file no, value 0~0x1F allowed  
Parameters  
valueFile file settings {@link ValueFileEntity }  
SdkResult.Success success  
Return  
SdkResult.Fail fail  
SdkResult.Param\_In\_Invalid Parameter is not legitimate  
SdkResult.TimeOut timeout  
ValueFileEntity  
Attributes Description  
commSettings 0x00 or 0x02 --- Plain communication  
0x01 --- Plain communication secured by MACing  
0x03--- Fully enciphered communication  
byte readAccessRightKeyNum Access right capability, 0x0E means free access, and  
0x0F means deny access. the reference number of the  
key which needs to be authenticated prior to Read  
Access and Read&Write Access  
byte writeAccessRightKeyNum the reference number of the key which needs to be  
authentication prior to Write Access and Read&Write  
Access  
Confidential 168  
| Function | create files for the storage and manipulation of 32bit signed integer values within an existing application on the PICC ValueFiles feature always the integrated backup mechanism. Therefore every access changing the value needs to be validated using the CommitTransaction command 1. It is necessary to validate the updated value with a CommitTransaction command. An AbortTransaction command will invalidate all changes 2. The value modifications of Credit, Debit and LimitedCredit commands are cumulated until a CommitTransaction command is issued. | None |  
| --- | --- | --- |  
| Parameters | fileNo | the specific file no, value 0~0x1F allowed |  
| None | valueFile | file settings {@link ValueFileEntity } |  
| Return | SdkResult.Success | success |  
| None | SdkResult.Fail | fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is not legitimate |  
| None | SdkResult.TimeOut | timeout |  
| Attributes | Description |  
| --- | --- |  
| commSettings | 0x00 or 0x02 --- Plain communication 0x01 --- Plain communication secured by MACing 0x03--- Fully enciphered communication |  
| byte readAccessRightKeyNum | Access right capability, 0x0E means free access, and 0x0F means deny access. the reference number of the key which needs to be authenticated prior to Read Access and Read&Write Access |  
| byte writeAccessRightKeyNum | the reference number of the key which needs to be authentication prior to Write Access and Read&Write Access |

## Página 169

SmartPos API Reference Manual: 09/08/21  
Byte the reference number of the key which needs to be  
readAndWriteAccessRightKeyNum authentication prior to Read&Write Access  
byte changeAccessRightKeyNum the reference number of the key,which is necessary to  
be authenticated with in order to change the access  
rights for the file and to link each access right to key  
numbers  
int lowerLimit lower limit of the value file, only available when  
file\_type = 0x02  
int upperLimit upper limit of the value file, only available when  
file\_type = 0x02  
int initValue the initial value of this value file, only available when  
file\_type = 0x02  
boolean limitedCreditEnabled if the LimitedCredit command is allowed for this file,  
only available when file\_type = 0x02  
3.16.26 createLinearRecordFile  
int createLinearRecordFile(byte fileNo, RecordFileEntity recordFile);  
Prototype  
create files for multiple storage of structural data, for example for loyalty  
Function  
programs, within an existing application on the PICC  
1. Once the file is filled completely with data records, further writing to the file is  
not possible unless it is cleared, see command ClearRecordFile.  
2. Linear Record Files feature always the integrated backup mechanism. Therefore  
every access appending a record needs to be validated using the  
CommitTransaction command  
fileNo the specific file no, value 0~0x1F allowed  
Parameters  
valueFile file settings {@link ValueFileEntity }  
SdkResult.Success success  
Return  
SdkResult.Fail fail  
SdkResult.Param\_In\_Invalid Parameter is not legitimate  
Confidential 169  
| Byte readAndWriteAccessRightKeyNum | the reference number of the key which needs to be authentication prior to Read&Write Access |  
| --- | --- |  
| byte changeAccessRightKeyNum | the reference number of the key,which is necessary to be authenticated with in order to change the access rights for the file and to link each access right to key numbers |  
| int lowerLimit | lower limit of the value file, only available when file\_type = 0x02 |  
| int upperLimit | upper limit of the value file, only available when file\_type = 0x02 |  
| int initValue | the initial value of this value file, only available when file\_type = 0x02 |  
| boolean limitedCreditEnabled | if the LimitedCredit command is allowed for this file, only available when file\_type = 0x02 |  
| Prototype | int createLinearRecordFile(byte fileNo, RecordFileEntity recordFile); | None |  
| --- | --- | --- |  
| Function | create files for multiple storage of structural data, for example for loyalty programs, within an existing application on the PICC 1. Once the file is filled completely with data records, further writing to the file is not possible unless it is cleared, see command ClearRecordFile. 2. Linear Record Files feature always the integrated backup mechanism. Therefore every access appending a record needs to be validated using the CommitTransaction command | None |  
| Parameters | fileNo | the specific file no, value 0~0x1F allowed |  
| None | valueFile | file settings {@link ValueFileEntity } |  
| Return | SdkResult.Success | success |  
| None | SdkResult.Fail | fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is not legitimate |

## Página 170

SmartPos API Reference Manual: 09/08/21  
SdkResult.TimeOut timeout  
RecordFileEntity  
Attributes Description  
boolean isoFidEnable whether ISO/IEC 7816-4 File IDentifiers enabled  
(0x00 - disabled, 0x01-enabled)  
byte[] isoFid 2bytes ISO/IEC 7816-4 File IDentifiers  
commSettings 0x00 or 0x02 --- Plain communication  
0x01 --- Plain communication secured by MACing  
0x03--- Fully enciphered communication  
byte readAccessRightKeyNum Access right capability, 0x0E means free access, and  
0x0F means deny access. the reference number of the  
key which needs to be authenticated prior to Read  
Access and Read&Write Access  
byte writeAccessRightKeyNum the reference number of the key which needs to be  
authentication prior to Write Access and Read&Write  
Access  
Byte the reference number of the key which needs to be  
readAndWriteAccessRightKeyNum authentication prior to Read&Write Access  
byte changeAccessRightKeyNum the reference number of the key,which is necessary  
to be authenticated with in order to change the  
access rights for the file and to link each access right  
to key numbers  
int recordSize the size of one single record (as deefined at file  
creation), only available when file\_type = 0x03 or  
file\_type = 0x04  
int maxNumberOfRecords the maximum number of records within the record  
file (as defined at file creation) , only available when  
file\_type = 0x03 or file\_type = 0x04  
byte whether specifies Random write access option,  
specifiesRandomWriteAccessOption (0x00 - not, 0x01 - yes), only available when file\_type  
= 0x03 or file\_type = 0x04  
boolean whether allowed Random write access, only  
allowedRandomWriteAccess available when file\_type = 0x03 or file\_type = 0x04  
Confidential 170  
| | SdkResult.TimeOut | timeout |  
| --- | --- | --- |  
| Attributes | Description |  
| --- | --- |  
| boolean isoFidEnable | whether ISO/IEC 7816-4 File IDentifiers enabled (0x00 - disabled, 0x01-enabled) |  
| byte[] isoFid | 2bytes ISO/IEC 7816-4 File IDentifiers |  
| commSettings | 0x00 or 0x02 --- Plain communication 0x01 --- Plain communication secured by MACing 0x03--- Fully enciphered communication |  
| byte readAccessRightKeyNum | Access right capability, 0x0E means free access, and 0x0F means deny access. the reference number of the key which needs to be authenticated prior to Read Access and Read&Write Access |  
| byte writeAccessRightKeyNum | the reference number of the key which needs to be authentication prior to Write Access and Read&Write Access |  
| Byte readAndWriteAccessRightKeyNum | the reference number of the key which needs to be authentication prior to Read&Write Access |  
| byte changeAccessRightKeyNum | the reference number of the key,which is necessary to be authenticated with in order to change the access rights for the file and to link each access right to key numbers |  
| int recordSize | the size of one single record (as deefined at file creation), only available when file\_type = 0x03 or file\_type = 0x04 |  
| int maxNumberOfRecords | the maximum number of records within the record file (as defined at file creation) , only available when file\_type = 0x03 or file\_type = 0x04 |  
| byte specifiesRandomWriteAccessOption | whether specifies Random write access option, (0x00 - not, 0x01 - yes), only available when file\_type = 0x03 or file\_type = 0x04 |  
| boolean allowedRandomWriteAccess | whether allowed Random write access, only available when file\_type = 0x03 or file\_type = 0x04 |

## Página 171

SmartPos API Reference Manual: 09/08/21  
3.16.27 createCyclicRecordFile  
int createCyclicRecordFile(byte fileNo, RecordFileEntity recordFile);  
Prototype  
create files for multiple storage of structural data, for example for loyalty  
Function  
programs, within an existing application on the PICC  
1. Once the file is filled completely with data records, further writing to the file is  
not possible unless it is cleared, see command ClearRecordFile.  
2. Linear Record Files feature always the integrated backup mechanism. Therefore  
every access appending a record needs to be validated using the  
CommitTransaction command  
fileNo the specific file no, value 0~0x1F allowed  
Parameters  
valueFile file settings {@link ValueFileEntity }  
SdkResult.Success success  
Return  
SdkResult.Fail fail  
SdkResult.Param\_In\_Invalid Parameter is not legitimate  
SdkResult.TimeOut timeout  
3.16.28 deleteFile  
int deleteFile(byte fileNo);  
Prototype  
permanently deactivates a file within the file directory of the currently selected  
Function  
application.  
1. The operation of this command invalidates the file directory entry of the  
specified file which means that the file can't be accessed anymore.  
Confidential 171  
| Prototype | int createCyclicRecordFile(byte fileNo, RecordFileEntity recordFile); | None |  
| --- | --- | --- |  
| Function | create files for multiple storage of structural data, for example for loyalty programs, within an existing application on the PICC 1. Once the file is filled completely with data records, further writing to the file is not possible unless it is cleared, see command ClearRecordFile. 2. Linear Record Files feature always the integrated backup mechanism. Therefore every access appending a record needs to be validated using the CommitTransaction command | None |  
| Parameters | fileNo | the specific file no, value 0~0x1F allowed |  
| None | valueFile | file settings {@link ValueFileEntity } |  
| Return | SdkResult.Success | success |  
| None | SdkResult.Fail | fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is not legitimate |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | int deleteFile(byte fileNo); |  
| --- | --- |  
| Function | permanently deactivates a file within the file directory of the currently selected application. 1. The operation of this command invalidates the file directory entry of the specified file which means that the file can't be accessed anymore. |

## Página 172

SmartPos API Reference Manual: 09/08/21  
2. Depending on the application master key settings, a preceding authentication  
with the application master key is required.  
3. Allocated memory blocks associated with the deleted file are not set free. The  
FileNo of the deleted file can be re-used to create a new file within that  
application.  
4. To release memory blocks for re-use, the whole PICC user NV-memory needs to  
be erased using the FormatPICC command.  
fileNo the file number within the file directory of the  
Parameters  
currently selected application.  
SdkResult.Success success  
Return  
SdkResult.Fail fail  
SdkResult.Param\_In\_Invalid  
Parameter is not legitimate  
SdkResult.TimeOut timeout  
3.16.29 readData  
byte[] readData(byte fileNo, byte commSettings, int offset, int len);  
Prototype  
Read data from Standard Data Files or Backup Data Files  
Function  
1. This offset has to be in the range from 0 to file size -1.  
2. If the len is coded as 0, the entire data file, starting from the position specified in  
the offset value, is read.  
3. If Backup Data Files are read after writing to them, but before issuing the  
CommitTransaction command, the ReadData command will always retrieve the  
old, unchanged data stored in the PICC. All data written to a Backup Data File is  
validated and externally "visible" for a ReadData command only after a  
CommitTransaction command.  
4. The Read command requires a preceding authentication either with the key  
specified for "Read" or "Read&Write" access  
fileNo the file number  
Confidential 172  
| | 2. Depending on the application master key settings, a preceding authentication with the application master key is required. 3. Allocated memory blocks associated with the deleted file are not set free. The FileNo of the deleted file can be re-used to create a new file within that application. 4. To release memory blocks for re-use, the whole PICC user NV-memory needs to be erased using the FormatPICC command. | None |  
| --- | --- | --- |  
| Parameters | fileNo | the file number within the file directory of the currently selected application. |  
| Return | SdkResult.Success | success |  
| None | SdkResult.Fail | fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is not legitimate |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | byte[] readData(byte fileNo, byte commSettings, int offset, int len); | None |  
| --- | --- | --- |  
| Function | Read data from Standard Data Files or Backup Data Files 1. This offset has to be in the range from 0 to file size -1. 2. If the len is coded as 0, the entire data file, starting from the position specified in the offset value, is read. 3. If Backup Data Files are read after writing to them, but before issuing the CommitTransaction command, the ReadData command will always retrieve the old, unchanged data stored in the PICC. All data written to a Backup Data File is validated and externally "visible" for a ReadData command only after a CommitTransaction command. 4. The Read command requires a preceding authentication either with the key specified for "Read" or "Read&Write" access | None |  
| | fileNo | the file number |

## Página 173

SmartPos API Reference Manual: 09/08/21  
commSettings 0x00 or 0x02 --- Plain communication  
Parameters  
0x01 --- Plain communication secured by MACing  
0x03 --- Fully enciphered communication  
offset the starting position for the read operation within the  
file  
len the number of data bytes want to be read  
return the out data  
Return  
3.16.30 writeData  
int writeData(byte fileNo, byte commSettings, int offset, byte[] data);  
Prototype  
Write data to Standard Data Files and Backup Data Files.  
Function  
1. The Write command requires a preceding authentication either with the key  
specified for "Write" or "Read&Write" access.  
2. If the WriteData operation is performed on a Backup Data File, it is necessary to  
validate the written data with a CommitTransaction command. An  
AbortTransaction command will invalidate all changes.  
fileNo the file number  
Parameters  
commSettings 0x00 or 0x02 --- Plain communication  
0x01 --- Plain communication secured by MACing  
0x03 --- Fully enciphered communication  
offset the starting position for the write operation within  
the file  
data Data to send  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
Confidential 173  
| Parameters | commSettings | 0x00 or 0x02 --- Plain communication 0x01 --- Plain communication secured by MACing 0x03 --- Fully enciphered communication |  
| --- | --- | --- |  
| None | offset | the starting position for the read operation within the file |  
| None | len | the number of data bytes want to be read |  
| Return | return the out data | None |  
| Prototype | int writeData(byte fileNo, byte commSettings, int offset, byte[] data); | None |  
| --- | --- | --- |  
| Function | Write data to Standard Data Files and Backup Data Files. 1. The Write command requires a preceding authentication either with the key specified for "Write" or "Read&Write" access. 2. If the WriteData operation is performed on a Backup Data File, it is necessary to validate the written data with a CommitTransaction command. An AbortTransaction command will invalidate all changes. | None |  
| Parameters | fileNo | the file number |  
| None | commSettings | 0x00 or 0x02 --- Plain communication 0x01 --- Plain communication secured by MACing 0x03 --- Fully enciphered communication |  
| None | offset | the starting position for the write operation within the file |  
| None | data | Data to send |  
| Return | SdkResult.Success | Success |  
| None | SdkResult.Fail | Fail |

## Página 174

SmartPos API Reference Manual: 09/08/21  
SdkResult.Param\_In\_Invalid Parameter is invalid  
SdkResult.TimeOut timeout  
3.16.31 getValue  
int getValue(byte fileNo, byte commSettings);  
Prototype  
Read the currently stored value from Value Files.  
Function  
1. The GetValue command requires a preceding authentication with the key  
specified for Read, Write or Read&Write access  
2. After updating a value file's value but before issuing the CommitTransaction  
command, the GetValue command will always retrieve the old, unchanged value  
which is still the valid one.  
fileNo the file number  
Parameters  
commSettings 0x00 or 0x02 --- Plain communication  
0x01 --- Plain communication secured by MACing  
0x03 --- Fully enciphered communication  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
SdkResult.Param\_In\_Invalid Parameter is invalid  
SdkResult.TimeOut timeout  
3.16.32 credit  
int credit(byte fileNo, byte commSettings, int value);  
Prototype  
Increase a value stored in a Value File.  
Function  
Confidential 174  
| | SdkResult.Param\_In\_Invalid | Parameter is invalid |  
| --- | --- | --- |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | int getValue(byte fileNo, byte commSettings); | None |  
| --- | --- | --- |  
| Function | Read the currently stored value from Value Files. 1. The GetValue command requires a preceding authentication with the key specified for Read, Write or Read&Write access 2. After updating a value file's value but before issuing the CommitTransaction command, the GetValue command will always retrieve the old, unchanged value which is still the valid one. | None |  
| Parameters | fileNo | the file number |  
| None | commSettings | 0x00 or 0x02 --- Plain communication 0x01 --- Plain communication secured by MACing 0x03 --- Fully enciphered communication |  
| Return | SdkResult.Success | Success |  
| None | SdkResult.Fail | Fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is invalid |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | int credit(byte fileNo, byte commSettings, int value); |  
| --- | --- |  
| Function | Increase a value stored in a Value File. |

## Página 175

SmartPos API Reference Manual: 09/08/21  
1. Credit commands do NEVER modify the Limited Credit Value of a Value file.  
However, if the Limited Credit Value needs to be set to 0, a LimitedCredit with  
value 0 can be used.  
2. The Credit command requires a preceding authentication with the key specified  
for "Read&Write" access.  
fileNo the file number  
Parameters  
commSettings 0x00 or 0x02 --- Plain communication  
0x01 --- Plain communication secured by MACing  
0x03 --- Fully enciphered communication  
value the value which will be subtracted from the current  
value stored in the file. Only positive values are  
allowed for the Credit command.  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
SdkResult.Param\_In\_Invalid Parameter is invalid  
SdkResult.TimeOut timeout  
3.16.33 debit  
int debit(byte fileNo, byte commSettings, int value);  
Prototype  
Decrease a value stored in a Value File.  
Function  
1. The Debit command requires a preceding authentication with one of the keys  
specified for Read, Write or Read&Write access.  
2. If the usage of the LimitedCredit feature is enabled, the new limit for a  
subsequent LimitedCredit command is set to the sum of Debit commands within  
one transaction before issuing a CommitTransaction command. This assures that a  
LimitedCredit command can not re-book more values than a debiting transaction  
deducted before.  
Confidential 175  
| | 1. Credit commands do NEVER modify the Limited Credit Value of a Value file. However, if the Limited Credit Value needs to be set to 0, a LimitedCredit with value 0 can be used. 2. The Credit command requires a preceding authentication with the key specified for "Read&Write" access. | None |  
| --- | --- | --- |  
| Parameters | fileNo | the file number |  
| None | commSettings | 0x00 or 0x02 --- Plain communication 0x01 --- Plain communication secured by MACing 0x03 --- Fully enciphered communication |  
| None | value | the value which will be subtracted from the current value stored in the file. Only positive values are allowed for the Credit command. |  
| Return | SdkResult.Success | Success |  
| None | SdkResult.Fail | Fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is invalid |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | int debit(byte fileNo, byte commSettings, int value); |  
| --- | --- |  
| Function | Decrease a value stored in a Value File. 1. The Debit command requires a preceding authentication with one of the keys specified for Read, Write or Read&Write access. 2. If the usage of the LimitedCredit feature is enabled, the new limit for a subsequent LimitedCredit command is set to the sum of Debit commands within one transaction before issuing a CommitTransaction command. This assures that a LimitedCredit command can not re-book more values than a debiting transaction deducted before. |

## Página 176

SmartPos API Reference Manual: 09/08/21  
fileNo the file number  
Parameters  
commSettings 0x00 or 0x02 --- Plain communication  
0x01 --- Plain communication secured by MACing  
0x03 --- Fully enciphered communication  
value the value which will be subtracted from the current  
value stored in the file. Only positive values are  
allowed for the Credit command.  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
SdkResult.Param\_In\_Invalid Parameter is invalid  
SdkResult.TimeOut timeout  
3.16.34 limitedCredit  
int limitedCredit(byte fileNo, byte commSettings, int value);  
Prototype  
Allows a limited increase of a value stored in a Value File without having full  
Function  
Read&Write permissions to the file. This feature can be enabled or disabled during  
value file creation.  
1. The LimitedCredit command requires a preceding authentication with the key  
specified for "Write" or "Read&Write" access.  
2. The value for LimitedCredit is limited to the sum of the Debit commands on this  
value file within the most recent transaction containing at least one Debit. After  
executing the LimitedCredit command the new limit is set to 0 regardless of the  
amount which has been re-booked. Therefore the LimitedCredit command can  
only be used once after a Debit transaction.  
fileNo the file number  
Parameters  
commSettings 0x00 or 0x02 --- Plain communication  
0x01 --- Plain communication secured by MACing  
Confidential 176  
| Parameters | fileNo | the file number |  
| --- | --- | --- |  
| None | commSettings | 0x00 or 0x02 --- Plain communication 0x01 --- Plain communication secured by MACing 0x03 --- Fully enciphered communication |  
| None | value | the value which will be subtracted from the current value stored in the file. Only positive values are allowed for the Credit command. |  
| Return | SdkResult.Success | Success |  
| None | SdkResult.Fail | Fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is invalid |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | int limitedCredit(byte fileNo, byte commSettings, int value); | None |  
| --- | --- | --- |  
| Function | Allows a limited increase of a value stored in a Value File without having full Read&Write permissions to the file. This feature can be enabled or disabled during value file creation. 1. The LimitedCredit command requires a preceding authentication with the key specified for "Write" or "Read&Write" access. 2. The value for LimitedCredit is limited to the sum of the Debit commands on this value file within the most recent transaction containing at least one Debit. After executing the LimitedCredit command the new limit is set to 0 regardless of the amount which has been re-booked. Therefore the LimitedCredit command can only be used once after a Debit transaction. | None |  
| Parameters | fileNo | the file number |  
| None | commSettings | 0x00 or 0x02 --- Plain communication 0x01 --- Plain communication secured by MACing |

## Página 177

SmartPos API Reference Manual: 09/08/21  
0x03 --- Fully enciphered communication  
value the value which will be subtracted from the current  
value stored in the file. Only positive values are  
allowed for the Credit command.  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
SdkResult.Param\_In\_Invalid Parameter is invalid  
SdkResult.TimeOut timeout  
3.16.35 writeRecord  
int writeRecord(byte fileNo, byte commSettings, int offset, int len, byte[] record);  
Prototype  
The WriteRecord command allows to write data to a record in a Cyclic or Linear  
Function  
Record File.  
1. The WriteRecord command appends one record at the end of the linear record  
file, it erases and overwrites the oldest record in case of a cyclic record file if it is  
already full. The entire new record is cleared before data is written to it.  
2. If no CommitTransaction command is sent after a WriteRecord command, the  
next WriteRecord command to the same file writes to the already created record.  
After sending a CommitTransaction command, a new WriteRecord command will  
create a new record in the record file. An AbortTransaction command will  
invalidate all changes  
3. After issuing a ClearRecordFile command, but before a CommitTransaction /  
AbortTransaction command, a WriteRecord command to the same record file will  
fail.  
4. The WriteRecord command requires a preceding authentication either with the  
key specified for "Write" or "Read&Write" access.  
Confidential 177  
| | | 0x03 --- Fully enciphered communication |  
| --- | --- | --- |  
| None | value | the value which will be subtracted from the current value stored in the file. Only positive values are allowed for the Credit command. |  
| Return | SdkResult.Success | Success |  
| None | SdkResult.Fail | Fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is invalid |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | int writeRecord(byte fileNo, byte commSettings, int offset, int len, byte[] record); |  
| --- | --- |  
| Function | The WriteRecord command allows to write data to a record in a Cyclic or Linear Record File. 1. The WriteRecord command appends one record at the end of the linear record file, it erases and overwrites the oldest record in case of a cyclic record file if it is already full. The entire new record is cleared before data is written to it. 2. If no CommitTransaction command is sent after a WriteRecord command, the next WriteRecord command to the same file writes to the already created record. After sending a CommitTransaction command, a new WriteRecord command will create a new record in the record file. An AbortTransaction command will invalidate all changes 3. After issuing a ClearRecordFile command, but before a CommitTransaction / AbortTransaction command, a WriteRecord command to the same record file will fail. 4. The WriteRecord command requires a preceding authentication either with the key specified for "Write" or "Read&Write" access. |

## Página 178

SmartPos API Reference Manual: 09/08/21  
fileNo the file number  
Parameters  
commSettings 0x00 or 0x02 --- Plain communication  
0x01 --- Plain communication secured by MACing  
0x03 --- Fully enciphered communication  
offset the offset within one single record, the value has to  
be in therange from 0 to record size - 1.  
len the length of data which is to be written to the  
record file, the value has to be in the range from 1 to  
record size - offset.  
record Record Information  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
SdkResult.Param\_In\_Invalid Parameter is invalid  
SdkResult.TimeOut timeout  
3.16.36 readRecords  
byte[] readRecords(byte fileNo, byte commSettings, int recordSize, int first, int  
Prototype  
num);  
The ReadRecords command allows to read out a set of complete records from a  
Function  
Cyclic or Linear Record File.  
1. In cyclic record files the maximum number of stored valid records is one less  
than the number of records specified in the CreateCyclicRecordFile command.  
2. A ReadRecords command on an empty record file (directly after creation or after  
a committed clearance will result in an error.  
3. The ReadRecords command requires a preceding authentication either with the  
key specified for "Read" or "Read&Write" access.  
fileNo the file number  
Confidential 178  
| Parameters | fileNo | the file number |  
| --- | --- | --- |  
| None | commSettings | 0x00 or 0x02 --- Plain communication 0x01 --- Plain communication secured by MACing 0x03 --- Fully enciphered communication |  
| None | offset | the offset within one single record, the value has to be in therange from 0 to record size - 1. |  
| None | len | the length of data which is to be written to the record file, the value has to be in the range from 1 to record size - offset. |  
| None | record | Record Information |  
| Return | SdkResult.Success | Success |  
| None | SdkResult.Fail | Fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is invalid |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | byte[] readRecords(byte fileNo, byte commSettings, int recordSize, int first, int num); | None |  
| --- | --- | --- |  
| Function | The ReadRecords command allows to read out a set of complete records from a Cyclic or Linear Record File. 1. In cyclic record files the maximum number of stored valid records is one less than the number of records specified in the CreateCyclicRecordFile command. 2. A ReadRecords command on an empty record file (directly after creation or after a committed clearance will result in an error. 3. The ReadRecords command requires a preceding authentication either with the key specified for "Read" or "Read&Write" access. | None |  
| | fileNo | the file number |

## Página 179

SmartPos API Reference Manual: 09/08/21  
commSettings 0x00 or 0x02 --- Plain communication  
Parameters  
0x01 --- Plain communication secured by MACing  
0x03 --- Fully enciphered communication  
recordSize the size of single record  
first the first record which is read out. In case of 0x00 the  
latest record is read out. The value must be in the  
range from 0x00 to number of existing records - 1.  
num the number of records to be read from the PICC.  
Records are always transmitted by the PICC in  
chronological order (= starting with the oldest,  
which is number of records ¨C 1 before the one  
addressed by the given offset). If this parameter  
is set to 0x00 then all records, from the oldest record  
up to and including the newest record(given by the  
offset parameter) are read.  
return Record Information  
Return  
3.16.37 clearRecordFile  
int clearRecordFile(byte fileNo);  
Prototype  
The ClearRecordFile command allows to reset a Cyclic or Linear Record File to the  
Function  
empty state.  
1. After executing the ClearRecordFile command but before CommitTransaction,  
all subsequent WriteRecord commands will fail.  
2. The ReadRecords command will return the old still valid records.  
3. After the CommitTransaction command is issued, a ReadRecords command will  
fail, WriteRecord commands will be successful.  
Confidential 179  
| Parameters | commSettings | 0x00 or 0x02 --- Plain communication 0x01 --- Plain communication secured by MACing 0x03 --- Fully enciphered communication |  
| --- | --- | --- |  
| None | recordSize | the size of single record |  
| None | first | the first record which is read out. In case of 0x00 the latest record is read out. The value must be in the range from 0x00 to number of existing records - 1. |  
| None | num | the number of records to be read from the PICC. Records are always transmitted by the PICC in chronological order (= starting with the oldest, which is number of records ¨C 1 before the one addressed by the given offset). If this parameter is set to 0x00 then all records, from the oldest record up to and including the newest record(given by the offset parameter) are read. |  
| Return | return Record Information | None |  
| Prototype | int clearRecordFile(byte fileNo); |  
| --- | --- |  
| Function | The ClearRecordFile command allows to reset a Cyclic or Linear Record File to the empty state. 1. After executing the ClearRecordFile command but before CommitTransaction, all subsequent WriteRecord commands will fail. 2. The ReadRecords command will return the old still valid records. 3. After the CommitTransaction command is issued, a ReadRecords command will fail, WriteRecord commands will be successful. |

## Página 180

SmartPos API Reference Manual: 09/08/21  
4. An AbortTransaction command (instead of CommitTransaction) will invalidate  
the clearance  
fileNo the file number  
Parameters  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
SdkResult.Param\_In\_Invalid Parameter is invalid  
SdkResult.TimeOut timeout  
3.16.38 commitTransaction  
int commitTransaction();  
Prototype  
The CommitTransaction command allows to validate all previous write access on  
Function  
Backup Data Files, Value Files and Record Files within one application.  
The CommitTransaction is typically the last command of a transaction before the  
ISO 14443-4 Deselect command or before proceeding with another application  
(SelectApplication command).  
null  
Parameters  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
SdkResult.Param\_In\_Invalid Parameter is invalid  
SdkResult.TimeOut timeout  
3.16.39 abortTransaction  
int abortTransaction();  
Prototype  
The AbortTransaction command allows to invalidate all previous write access on  
Function  
Backup Data Files, Value Files and Record Files within one application.  
Confidential 180  
| | 4. An AbortTransaction command (instead of CommitTransaction) will invalidate the clearance | None |  
| --- | --- | --- |  
| Parameters | fileNo | the file number |  
| Return | SdkResult.Success | Success |  
| None | SdkResult.Fail | Fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is invalid |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | int commitTransaction(); | None |  
| --- | --- | --- |  
| Function | The CommitTransaction command allows to validate all previous write access on Backup Data Files, Value Files and Record Files within one application. The CommitTransaction is typically the last command of a transaction before the ISO 14443-4 Deselect command or before proceeding with another application (SelectApplication command). | None |  
| Parameters | null | |  
| Return | SdkResult.Success | Success |  
| None | SdkResult.Fail | Fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is invalid |  
| None | SdkResult.TimeOut | timeout |  
| Prototype | int abortTransaction(); |  
| --- | --- |  
| Function | The AbortTransaction command allows to invalidate all previous write access on Backup Data Files, Value Files and Record Files within one application. |

## Página 181

SmartPos API Reference Manual: 09/08/21  
This is useful to cancel a transaction without the need for re-authentication to the  
PICC, which would lead to the same functionality.  
null  
Parameters  
SdkResult.Success Success  
Return  
SdkResult.Fail Fail  
SdkResult.Param\_In\_Invalid Parameter is invalid  
SdkResult.TimeOut timeout  
3.17 Mifare Ultralight card  
The Ultralight module class is responsible for managing operate the Mifare Ultralight card.  
UltralightCCardHandler ultralightCCardHandler = deviceEngine.getUltralightCCardHandler();  
3.17.1 authority  
Block certification.  
public int authority(byte[] keyData);  
Parameters:  
Parameter Description  
keyData Password authentication, 16 bytes(hex)  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
Confidential 181  
| | This is useful to cancel a transaction without the need for re-authentication to the PICC, which would lead to the same functionality. | None |  
| --- | --- | --- |  
| Parameters | null | |  
| Return | SdkResult.Success | Success |  
| None | SdkResult.Fail | Fail |  
| None | SdkResult.Param\_In\_Invalid | Parameter is invalid |  
| None | SdkResult.TimeOut | timeout |  
| Parameter | Description |  
| --- | --- |  
| keyData | Password authentication, 16 bytes(hex) |

## Página 182

SmartPos API Reference Manual: 09/08/21  
3.17.2 readBlock  
Read block data.  
public byte[] readBlock(byte blockNum);  
Parameters:  
Parameter Description  
blockNum block number  
Return Value:  
Success, return block data  
Failed, return null  
3.17.3 writeBlock  
Write block data.  
public int writeBlock(byte blockNum, byte[] writeData);  
Parameters:  
Parameter Description  
blockNum Block number  
writeData Write data  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
3.17.4 exchangeCmd  
Exchange data with command(use original command communicate with the card directly)  
public byte[] exchangeCmd(byte[] cmdData);  
Parameters:  
Parameter Description  
cmdData Command data send to card  
Return Value:  
Confidential 182  
| Parameter | Description |  
| --- | --- |  
| blockNum | block number |  
| Parameter | Description |  
| --- | --- |  
| blockNum | Block number |  
| writeData | Write data |  
| Parameter | Description |  
| --- | --- |  
| cmdData | Command data send to card |

## Página 183

SmartPos API Reference Manual: 09/08/21  
Success, return response data  
Failed ,return null  
3.18 Platform  
The platform module class is responsible for managing operate the device function, such as  
install application, uninstall application , reboot device, update firmware..etc.  
Platform platform = deviceEngine.getPlatform();  
3.18.1 installApp  
Install application.  
public int installApp(String appFilePath, final OnAppOperatListener listener);  
Parameters:  
Parameter Description  
appFilePath The path of the application.  
Note: the application must be signed if you want to install in production  
devices  
listener Install result callback  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid parameter invalid  
SdkResult.Fail other errors  
Other error code, please refer to Appendix  
3.18.2 unInstallApp  
Uninstall application.  
public int uninstallApp(String appPackageName, final OnAppOperatListener listener);  
Parameters:  
Confidential 183  
| Parameter | Description |  
| --- | --- |  
| appFilePath | The path of the application. Note: the application must be signed if you want to install in production devices |  
| listener | Install result callback |

## Página 184

SmartPos API Reference Manual: 09/08/21  
Parameter Description  
appPackageName Thepackae name of the application which you want to uninstall  
listener Uninstall result callback  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid parameter invalid  
SdkResult.Fail other errors  
Other error code, please refer to Appendix  
3.18.3 updateFirmware  
update firmware. The firmware must be provided by Nexgo .  
public int updateFirmware(String firmwareFilePath);  
Parameters:  
Parameter Description  
firmwareFilePath The path of the firmware which you want to update  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid parameter invalid  
SdkResult.Fail other errors  
Other error code, please refer to Appendix  
3.18.4 reboot  
Reboot device  
public int rebootDevice();  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
Confidential 184  
| Parameter | Description |  
| --- | --- |  
| appPackageName | Thepackae name of the application which you want to uninstall |  
| listener | Uninstall result callback |  
| Parameter | Description |  
| --- | --- |  
| firmwareFilePath | The path of the firmware which you want to update |

## Página 185

SmartPos API Reference Manual: 09/08/21  
3.18.5 shutDownDevice  
power off device  
public int shutDownDevice();  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
3.18.6 enableHomeButton  
enable home button  
public int enableHomeButton();  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
3.18.7 disableHomeButton  
disable home button  
public int disableHomeButton();  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
3.18.8 enableTaskButton  
enable task button  
public int enableTaskButton();  
Confidential 185

## Página 186

SmartPos API Reference Manual: 09/08/21  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
3.18.9 disableTaskButton  
disable task button  
public int disableTaskButton();  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
3.18.10 enableControlBar  
enable controlBar (popup menu)  
public int enableControlBar();  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
3.18.11 disableControlBar  
disable controlBar (popup menu)  
public int disableControlBar();  
Return Value:  
Confidential 186

## Página 187

SmartPos API Reference Manual: 09/08/21  
SdkResult.Success success  
SdkResult.Fail other errors  
3.18.12 enablePowerButton  
enable power button  
public int enablePowerButton;  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
3.18.13 disablePowerButton  
disable power button  
public int disablePowerButton();  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
3.18.14 setBeepMode  
set device beep mode, application can control the beep volume  
public void setBeepMode(BeepVolumeModeEnum beepMode, int volume);  
Parameters:  
Parameter Description  
beepMode BEEP\_MODE\_SYSTEM\_DEFAULT: system default  
BEEP\_MODE\_CUSTOM: application set the beep volume  
volume Beep volume, range 0 -100 (volume percentage)  
Confidential 187  
| Parameter | Description |  
| --- | --- |  
| beepMode | BEEP\_MODE\_SYSTEM\_DEFAULT: system default BEEP\_MODE\_CUSTOM: application set the beep volume |  
| volume | Beep volume, range 0 -100 (volume percentage) |

## Página 188

SmartPos API Reference Manual: 09/08/21  
BeepVolumeModeEnum  
Enumeration Name Description  
BEEP\_MODE\_SYSTEM\_DEFAULT system default  
BEEP\_MODE\_CUSTOM application set the beep volume  
Return Value:  
None  
3.18.15 enableUsbCdc  
enable usb cdc option  
public int enableUsbCdc ();  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
3.18.16 disableUsbCdc  
disable usb cdc option  
public int disableUsbCdc ();  
Return Value:  
SdkResult.Success success  
SdkResult.Fail other errors  
3.18.17 getUsbCdcStatus  
get usb cdc status  
Confidential 188  
| Enumeration Name | Description |  
| --- | --- |  
| BEEP\_MODE\_SYSTEM\_DEFAULT | system default |  
| BEEP\_MODE\_CUSTOM | application set the beep volume |

## Página 189

SmartPos API Reference Manual: 09/08/21  
public boolean getUsbCdcStatus ();  
Return Value:  
true enable  
false disable  
3.18.18 executeGeneralMethod  
execute general method  
public int executeGeneralMethod(int cmd, byte[] inParam, byte[] otherParam, byte[] outData)  
Parameters:  
Parameter Description  
cmd Cmd  
byte[] inParam Depends on the cmd  
byte[] Depends on the cmd  
otherParam  
byte[] outData Depends on the cmd  
Return Value:  
SdkResult.Success success  
SdkResult.Param\_In\_Invalid parameter invalid  
SdkResult.Fail other errors  
3.19 Usb Serial class  
Usb Serial class is responsible for managing POS usb serial port.  
Please note, need to use Nexgo special cable.  
Get the usb serial class objects:  
UsbSerial usbSerial = deviceEngine. getUsbSerial ();  
3.19.1 open  
Confidential 189  
| Parameter | Description |  
| --- | --- |  
| cmd | Cmd |  
| byte[] inParam | Depends on the cmd |  
| byte[] otherParam | Depends on the cmd |  
| byte[] outData | Depends on the cmd |

## Página 190

SmartPos API Reference Manual: 09/08/21  
Open usb serial port.  
public int open(UsbSerialCfgEntity entity, OnUsbSerialReadListener listener);  
Parameters:  
Parameter Description  
entity UsbSerialCfgEntity, usb Serial Info  
listener OnUsbSerialReadListener, the reading data will be sent by this listener.  
UsbSerialCfgEntity  
Attributes Description  
int vid The verdor id of the usb serial port  
int pid The product id of the usb serial port  
int bauRate The baud rate in the range of (bps):  
110,300,600,1200,2400,4800, 9600,14400,56000,19200,38400,57600,115200,230400  
int dataBits Data Bits Range: 5, 6, 7, 8  
char parity Test methods in the range : 'o' odd , 'e' parity, 'n' no parity  
int stopBits Stop bit value range : 1, 2  
Return Value:  
SdkResult.Success serial connection success  
SdkResult.Fail other errors  
3.19.2 close  
Close the usb serial port.  
public int close ();  
Parameters: None  
Return Value:  
SdkResult.Success close successfully  
SdkResult.Fail other errors  
Confidential 190  
| Parameter | Description |  
| --- | --- |  
| entity | UsbSerialCfgEntity, usb Serial Info |  
| listener | OnUsbSerialReadListener, the reading data will be sent by this listener. |  
| Attributes | Description |  
| --- | --- |  
| int vid | The verdor id of the usb serial port |  
| int pid | The product id of the usb serial port |  
| int bauRate | The baud rate in the range of (bps): 110,300,600,1200,2400,4800, 9600,14400,56000,19200,38400,57600,115200,230400 |  
| int dataBits | Data Bits Range: 5, 6, 7, 8 |  
| char parity | Test methods in the range : 'o' odd , 'e' parity, 'n' no parity |  
| int stopBits | Stop bit value range : 1, 2 |

## Página 191

SmartPos API Reference Manual: 09/08/21  
3.19.3 clrBuffer  
Clear the buffer.  
public void clrBuffer ();  
Parameters: None  
Return Value: None  
3.19.4 write  
Send data.  
public int write (byte [] data, int dataLen);  
Parameters:  
Parameter Description  
data Write data  
dataLen Data length  
Return Value:  
SdkResult.Success sent successfully  
SdkResult.Fail other errors  
3.19.5 read  
Receive data.  
public int read (byte [] buffer, int readLen);  
Parameters:  
Parameter Description  
buffer Buffer to receive data  
recvLen The maximum length of buffer, which is 4096 bytes  
Return Value:  
Successfully received returns the length of the received data  
SdkResult.Fail other errors  
Confidential 191  
| Parameter | Description |  
| --- | --- |  
| data | Write data |  
| dataLen | Data length |  
| Parameter | Description |  
| --- | --- |  
| buffer | Buffer to receive data |  
| recvLen | The maximum length of buffer, which is 4096 bytes |

## Página 192

SmartPos API Reference Manual: 09/08/21  
4 Callback information  
4.1 OnPrintListener  
Responsible for managing the printer class callback interface.  
4.1.1 onPrintResult  
After executing the startPrint method, callback to print results.  
public void onPrintResult (int retCode);  
Parameters:  
Parameter Description  
retCode Print callback Results :  
SdkResult.Success success  
SdkResult.Printer\_Print\_Fail failed to print  
SdkResult.Printer\_PaperLack out of paper  
SdkResult.Printer\_UnFinished print unfinished  
SdkResult.Printer\_TooHot printer is overheating  
Return Value: None  
4.2 OnPinPadInputListener  
PIN pad class is responsible for the management callback interface.  
4.2.1 onInputResult  
After inputOnlinePin or inputOffinePin method is executed, it callback to show pin result.  
public void onInputResult (int retCode, byte [] data);  
Parameters:  
Parameter Description  
retCode Enter the result :  
SdkResult.Success success  
SdkResult.Fail failure  
SdkResult.PinPad\_Input\_Timeout input timeout  
SdkResult.PinPad\_Input\_Cancel cancel input  
Confidential 192  
| Parameter | Description |  
| --- | --- |  
| retCode | Print callback Results : SdkResult.Success success SdkResult.Printer\_Print\_Fail failed to print SdkResult.Printer\_PaperLack out of paper SdkResult.Printer\_UnFinished print unfinished SdkResult.Printer\_TooHot printer is overheating |  
| Parameter | Description |  
| --- | --- |  
| retCode | Enter the result : SdkResult.Success success SdkResult.Fail failure SdkResult.PinPad\_Input\_Timeout input timeout SdkResult.PinPad\_Input\_Cancel cancel input |

## Página 193

SmartPos API Reference Manual: 09/08/21  
SdkResulr.PinPad\_No\_Pin\_Input no password entered, press Enter  
directly  
data When retCode == SdkResult.Success return pin  
Otherwise return null  
Return Value: None  
4.2.2 onSendKey  
After inputText, inputOnlinePin, inputOfflinePin method, this is executed, callback the key input, when  
input password, number key will return KEYCODE\_STAR.  
public void onSendKey (byte keyCode);  
Parameters:  
Parameter Description  
keyCode Input key value  
Key-Value  
Constant Name Constant Value Description  
KEYCODE\_0 0x30 (byte) 0  
KEYCODE\_1 0x31 (byte) 1  
KEYCODE\_2 0x32 (byte) 2  
KEYCODE\_3 0x33 (byte) 3  
KEYCODE\_4 0x34 (byte) 4  
KEYCODE\_5 0x35 (byte) 5  
KEYCODE\_6 0x36 (byte) 6  
KEYCODE\_7 0x37 (byte) 7  
KEYCODE\_8 0x38 (byte) 8  
KEYCODE\_9 0x39 (byte) 9  
KEYCODE\_ the STAR 0x2a (byte) \*  
KEYCODE\_OCTOTHORPE 0x23 (byte) #  
KEYCODE\_CANCEL 0x18 (byte) Cancel key  
KEYCODE\_BACKSPACE 0x08 (byte) Backspace  
KEYCODE\_CLEAR 0xfe (byte) Clear key  
KEYCODE\_CONFIRM 0x0d (byte) Enter  
Return Value: None  
Confidential 193  
| | SdkResulr.PinPad\_No\_Pin\_Input no password entered, press Enter directly |  
| --- | --- |  
| data | When retCode == SdkResult.Success return pin Otherwise return null |  
| Parameter | Description |  
| --- | --- |  
| keyCode | Input key value |  
| Constant Name | Constant Value | Description |  
| --- | --- | --- |  
| KEYCODE\_0 | 0x30 (byte) | 0 |  
| KEYCODE\_1 | 0x31 (byte) | 1 |  
| KEYCODE\_2 | 0x32 (byte) | 2 |  
| KEYCODE\_3 | 0x33 (byte) | 3 |  
| KEYCODE\_4 | 0x34 (byte) | 4 |  
| KEYCODE\_5 | 0x35 (byte) | 5 |  
| KEYCODE\_6 | 0x36 (byte) | 6 |  
| KEYCODE\_7 | 0x37 (byte) | 7 |  
| KEYCODE\_8 | 0x38 (byte) | 8 |  
| KEYCODE\_9 | 0x39 (byte) | 9 |  
| KEYCODE\_ the STAR | 0x2a (byte) | \* |  
| KEYCODE\_OCTOTHORPE | 0x23 (byte) | # |  
| KEYCODE\_CANCEL | 0x18 (byte) | Cancel key |  
| KEYCODE\_BACKSPACE | 0x08 (byte) | Backspace |  
| KEYCODE\_CLEAR | 0xfe (byte) | Clear key |  
| KEYCODE\_CONFIRM | 0x0d (byte) | Enter |

## Página 194

SmartPos API Reference Manual: 09/08/21  
4.3 OnScanner Listener  
Responsible for managing the camera scan code results callback.  
4.3.1 onInitResult  
Initialize the camera configuration callback.  
public void onInitResult (int retCode);  
Parameters:  
Parameter Description  
retCode Enter the result:  
SdkResult.Success success  
SdkResult.Fail failure  
Return Value: None  
4.3.2 onScannerResult  
Scan code results callback.  
public void onScannerResult (int retCode, String data);  
Parameters:  
Parameter Description  
retCode Enter the result :  
SdkResult.Success success  
SdkResult.Fail failure  
SdkResult.Param\_In\_Invalid Parameter error  
SdkResult. TimeOut scan code timeout  
SdkResult. Scanner\_Customer\_Exit voluntary user withdrawal  
data Scan code Results  
Return Value: None  
4.4 OnCardInfoListener  
Reader class is responsible for managing the callback interface.  
4.4.1 onCardInfo  
Confidential 194  
| Parameter | Description |  
| --- | --- |  
| retCode | Enter the result: SdkResult.Success success SdkResult.Fail failure |  
| Parameter | Description |  
| --- | --- |  
| retCode | Enter the result : SdkResult.Success success SdkResult.Fail failure SdkResult.Param\_In\_Invalid Parameter error SdkResult. TimeOut scan code timeout SdkResult. Scanner\_Customer\_Exit voluntary user withdrawal |  
| data | Scan code Results |

## Página 195

SmartPos API Reference Manual: 09/08/21  
After executing the searchCard method, callback reader results.  
public void onCardInfo (int retCode, CardInfoEntity cardInfo);  
Parameters:  
Parameter Description  
retCode Enter the result :  
SdkResult.Success success  
SdkResult.Fail failure  
SdkResult.TimeOut timeout  
cardInfo When retCode == SdkResult.Success return card information  
Otherwise return null  
CardInfoEntity  
Attributes Description  
String cardNo Card number  
CardSlotTypeEnum cardExistslot CardSlotType  
RfCardTypeEnum rfCardType RfCardTyp  
String tk1 track one  
String tk2 tracks two  
String tk3 tracks three  
String expiredDate Card is valid  
String serviceCode Service Code  
boolean isTk1Valid A track LRC is correct  
boolean isTk2Valid Two tracks LRC is correct  
boolean isTk3Valid Three tracks LRC is correct  
boolean isICC If mag card has chip flag  
String csn Card serial number, only returnd in  
OnEmvProcessListener.onConfirmCardNo  
CardSlotTypeEnum  
Enumeration Name Description  
ICC1 The I C slot 1  
ICC2 The I C slot 2  
ICC3 The I C slot 3  
PSAM1 PSAM slot 1  
PSAM2 PSAM slot 1  
PSAM3 PSAM slot 1  
Confidential 195  
| Parameter | Description |  
| --- | --- |  
| retCode | Enter the result : SdkResult.Success success SdkResult.Fail failure SdkResult.TimeOut timeout |  
| cardInfo | When retCode == SdkResult.Success return card information Otherwise return null |  
| Attributes | Description |  
| --- | --- |  
| String cardNo | Card number |  
| CardSlotTypeEnum cardExistslot | CardSlotType |  
| RfCardTypeEnum rfCardType | RfCardTyp |  
| String tk1 | track one |  
| String tk2 | tracks two |  
| String tk3 | tracks three |  
| String expiredDate | Card is valid |  
| String serviceCode | Service Code |  
| boolean isTk1Valid | A track LRC is correct |  
| boolean isTk2Valid | Two tracks LRC is correct |  
| boolean isTk3Valid | Three tracks LRC is correct |  
| boolean isICC | If mag card has chip flag |  
| String csn | Card serial number, only returnd in OnEmvProcessListener.onConfirmCardNo |  
| Enumeration Name | Description |  
| --- | --- |  
| ICC1 | The I C slot 1 |  
| ICC2 | The I C slot 2 |  
| ICC3 | The I C slot 3 |  
| PSAM1 | PSAM slot 1 |  
| PSAM2 | PSAM slot 1 |  
| PSAM3 | PSAM slot 1 |

## Página 196

SmartPos API Reference Manual: 09/08/21  
RF Contactless card slot  
SWIPE Magnetic stripe card slot  
RfCardTypeEnum  
Enumeration Name Description  
TYPE\_A\_CPU  
TYPE\_B\_CPU  
S50  
FELICA  
S70  
ULTRALIGHT  
MEMORY\_OTHER  
S50\_PRO  
S70\_PRO  
Return Value: None  
4.4.2 onSwipeIncorrect  
After executing searchCard method, will be callback when a swipe error occurs. This callback  
is a process callback, not a result callback  
public void onSwipeIncorrect();  
Return Value: None  
4.4.3 onMultipleCards  
After executing searchCard method，will be callback when find multiple contactless cards. This  
callback is a process callback, not a result callback  
public void onMultipleCards();  
Return Value: None  
4.5 OnEMVProcessListener2  
Responsible for managing the EMV class callback interface.  
Confidential 196  
| RF | Contactless card slot |  
| --- | --- |  
| SWIPE | Magnetic stripe card slot |  
| Enumeration Name | Description |  
| --- | --- |  
| TYPE\_A\_CPU | |  
| TYPE\_B\_CPU | |  
| S50 | |  
| FELICA | |  
| S70 | |  
| ULTRALIGHT | |  
| MEMORY\_OTHER | |  
| S50\_PRO | |  
| S70\_PRO | |

## Página 197

SmartPos API Reference Manual: 09/08/21  
4.5.1 onSelApp  
After EmvProcess executed, if card have multi-application, onSelApp callback will be executed .it will  
show app-list to let the user to select the application. Then call EmvHandler2.onSetSelAppResponse.  
public void onSelApp (List appNameList, List appInfoList, boolean  
isFirstSelect);  
Parameters:  
Parameter Description  
appNameList Application displays a list of names  
appInfoList Candidate application information card  
isFirstSelect Whether making the selection for the first time  
CandidateAppInfoEntity  
Attributes Description  
byte [] aid AID  
byte [] appLabel Apply the label  
byte [] preferName Application Preferred Name  
byte priority Application Priority Indicator  
byte [] langPrefer The preferred language  
byte icti Issuer Code Table Index  
Return Value: None  
4.5.2 onTransInitBeforeGPO  
After EmvProcess executed, before excute GPO, the EMV will callback this method. User can call setTlv  
method to set personalized tags. User can set it or not.(This is suit for both EMV contact and contactless  
flow), then call EmvHandler2. onSetContactlessTapCardResponse.  
public void onTransInitBeforeGPO ();  
Return Value: None  
4.5.3 onConfirmCardNo  
Confidential 197  
| Parameter | Description |  
| --- | --- |  
| appNameList | Application displays a list of names |  
| appInfoList | Candidate application information card |  
| isFirstSelect | Whether making the selection for the first time |  
| Attributes | Description |  
| --- | --- |  
| byte [] aid | AID |  
| byte [] appLabel | Apply the label |  
| byte [] preferName | Application Preferred Name |  
| byte priority | Application Priority Indicator |  
| byte [] langPrefer | The preferred language |  
| byte icti | Issuer Code Table Index |

## Página 198

SmartPos API Reference Manual: 09/08/21  
After EmvProcess executed, confirm the card number, then  
call EmvHandler2.onSetConfirmCardNoResponse.  
public void onConfirmCardNo (String cardNo);  
Parameters:  
Parameter Description  
cardNo card number  
Return Value: None  
4.5.4 onCardHolderInputPin  
EmvProcess executing the method, enter the password, to be called  
EmvHandler2.onSetPinInputResponse.  
public void onCardHolderInputPin (boolean isOnlinePin, int leftTimes);  
Parameters:  
Parameter Description  
isOnlinePin Is online password  
leftTimes Enter the remaining number of times for the offline PIN  
Return Value: None  
4.5.5 onContactlessTapCardAgain  
EmvProcess executing the method, Callback the second read card .(When host response the script with  
contactless EMV transaction.) The application should re-search contactless card, then call  
EmvHandler2. onSetContactlessTapCardResponse to notify EMV continue procress.  
public void onContactlessTapCardAgain ();  
Return Value: None  
Note: for amex contactless, the method will called by kernel, when process case “please see phone”  
4.5.6 onOnlineProc  
EmvProcess executing the method, means EMV kernel request online process, Then the application  
should call method getTlv to get the EMV tags, then send request message to the host. After host  
Confidential 198  
| Parameter | Description |  
| --- | --- |  
| cardNo | card number |  
| Parameter | Description |  
| --- | --- |  
| isOnlinePin | Is online password |  
| leftTimes | Enter the remaining number of times for the offline PIN |

## Página 199

SmartPos API Reference Manual: 09/08/21  
response, the application should call EmvHandler2.onSetOnlineProcResponse to notify the EMV kernel  
to do the second auth.  
public void onOnlineProc ();  
Parameters: None  
Return Value: None  
4.5.7 onPrompt  
EmvProcess executing the method, notify the application prompt information to the user, Then the  
application should call EmvHandler2. onPromptResponse to notify the EMV kernel to continue the flow.  
public void onPrompt(PromptEnum prompt);  
Parameters:  
Parameters Description  
prompt enum  
PromptEnum  
enum Description  
APP\_SELECTION\_IS\_NOT\_ACCEPTED Application is not accepted, please try again  
OFFLINE\_PIN\_INCORRECT\_TRY\_AGAIN Offline pin incorrect, please try again  
OFFLINE\_PIN\_INCORRECT Offline pin incorrect  
OFFLINE\_PIN\_CORRECT Offline pin correct  
Return Value: None  
4.5.8 onRemoveCard  
EmvProcess executing the method, notify the application contactless card can be remove from the card  
reader, Then call EmvHandler2. onSetRemoveCardResponse to notify the EMV kernel to continue the  
flow.  
public void onRemoveCard ();  
Parameters: None  
Confidential 199  
| Parameters | Description |  
| --- | --- |  
| prompt | enum |  
| enum | Description |  
| --- | --- |  
| APP\_SELECTION\_IS\_NOT\_ACCEPTED | Application is not accepted, please try again |  
| OFFLINE\_PIN\_INCORRECT\_TRY\_AGAIN | Offline pin incorrect, please try again |  
| OFFLINE\_PIN\_INCORRECT | Offline pin incorrect |  
| OFFLINE\_PIN\_CORRECT | Offline pin correct |

## Página 200

SmartPos API Reference Manual: 09/08/21  
Return Value: None  
4.5.9 onFinish  
EmvProcess executing the method, means all the EMV flow is finish. The retcode will indicate the EMV  
transaction result.  
public void onFinish (int retCode, EmvProcessResultEntity entity);  
Parameters:  
Parameter Description  
retCode Enter the result :  
SdkResult.Success success  
SdkResult.Fail failure  
Other return results please refer to the EMV class table 3.7 or consult  
Appendix 5  
entity When retCode == SdkResult.Success return data  
Otherwise return null  
EmvProcessResultEntity  
Attributes Description  
byte [] scriptResult Script execution results  
List EMVlog Cards Blog List  
byte [] ecBalance Electronic cash balance  
EmvCardLogEntity  
Attributes Description  
boolean isAmtExist Whether the amount of presence  
String amt Amount of money  
boolean isOtherAmtExist Whether the existence of other  
String otherAmt Other Amount  
boolean isDateExist Date of the transaction if there are  
String transDate transaction date  
boolean isTimeExist The existence of transactions  
String transTime transaction hour  
boolean isCntCodeExist Whether there is a country code  
String cntCode country code  
boolean isCurExist Currency code if there  
Confidential 200  
| Parameter | Description |  
| --- | --- |  
| retCode | Enter the result : SdkResult.Success success SdkResult.Fail failure Other return results please refer to the EMV class table 3.7 or consult Appendix 5 |  
| entity | When retCode == SdkResult.Success return data Otherwise return null |  
| Attributes | Description |  
| --- | --- |  
| byte [] scriptResult | Script execution results |  
| List EMVlog | Cards Blog List |  
| byte [] ecBalance | Electronic cash balance |  
| Attributes | Description |  
| --- | --- |  
| boolean isAmtExist | Whether the amount of presence |  
| String amt | Amount of money |  
| boolean isOtherAmtExist | Whether the existence of other |  
| String otherAmt | Other Amount |  
| boolean isDateExist | Date of the transaction if there are |  
| String transDate | transaction date |  
| boolean isTimeExist | The existence of transactions |  
| String transTime | transaction hour |  
| boolean isCntCodeExist | Whether there is a country code |  
| String cntCode | country code |  
| boolean isCurExist | Currency code if there |

## Página 201

SmartPos API Reference Manual: 09/08/21  
String curCode Currency code  
boolean isAtcExist The existence of the transaction counter  
String atc Transaction Counter  
boolean is9CExist 9c transaction type whether there  
String serveType Transaction Type  
boolean isMerNameExist The existence of a business name  
String merName Business Name  
Return Value: None  
4.6 OnEMVProcessListener Deprecated  
Please note: All the Emvhandler method, do not recommend use it anymore.  
Responsible for managing the EMV class callback interface.  
4.6.1 onSelApp  
After EmvProcess executed, callback app-list to select the application, then call  
EmvHandler.onSetSelAppResponse.  
public void onSelApp (List appNameList, List appInfoList, boolean  
isFirstSelect);  
Parameters:  
Parameter Description  
appNameList Application displays a list of names  
appInfoList Candidate application information card  
isFirstSelect Whether making the selection for the first time  
CandidateAppInfoEntity  
Attributes Description  
byte [] aid AID  
byte [] appLabel Apply the label  
byte [] preferName Application Preferred Name  
byte priority Application Priority Indicator  
Confidential 201  
| String curCode | Currency code |  
| --- | --- |  
| boolean isAtcExist | The existence of the transaction counter |  
| String atc | Transaction Counter |  
| boolean is9CExist | 9c transaction type whether there |  
| String serveType | Transaction Type |  
| boolean isMerNameExist | The existence of a business name |  
| String merName | Business Name |  
| Parameter | Description |  
| --- | --- |  
| appNameList | Application displays a list of names |  
| appInfoList | Candidate application information card |  
| isFirstSelect | Whether making the selection for the first time |  
| Attributes | Description |  
| --- | --- |  
| byte [] aid | AID |  
| byte [] appLabel | Apply the label |  
| byte [] preferName | Application Preferred Name |  
| byte priority | Application Priority Indicator |

## Página 202

SmartPos API Reference Manual: 09/08/21  
byte [] langPrefer The preferred language  
byte icti Issuer Code Table Index  
Return Value: None  
4.6.2 onAfterFinalSelectedApp  
After EmvProcess executed, before excute GPO, the EMV will callback this method. User can call setTlv  
method to set personalized tags. User can set it or not.(This is suit for EMV contactless flow), then  
call EmvHandler. onSetAfterFinalSelectedAppResponse.  
public void onAfterFinalSelectedApp ();  
Return Value: None  
4.6.3 onRequestAmount  
After EmvProcess executed, callback request input amount (triggered when when the transaction  
amount has not been input), then call EmvHandler.onSetRequestAmountResponse.  
public void onRequestAmount ();  
Return Value: None  
4.6.4 onConfirmEcSwitch  
After EmvProcess method executed, whether use electronic cash (triggered when the transaction is set  
to support electronic cash, and the card also support e-cash), then call  
EmvHandler.onSetConfirmEcSwitchResponse.  
public void onConfirmEcSwitch ();  
Return Value: None  
4.6.5 onConfirmCardNo  
After EmvProcess executed, confirm the card number, then  
call EmvHandler.onSetConfirmCardNoResponse.  
public void onConfirmCardNo (String cardNo);  
Parameters:  
Confidential 202  
| byte [] langPrefer | The preferred language |  
| --- | --- |  
| byte icti | Issuer Code Table Index |

## Página 203

SmartPos API Reference Manual: 09/08/21  
Parameter Description  
cardNo card number  
Return Value: None  
4.6.6 onCardHolderInputPin  
EmvProcess executing the method, enter the password, to be called  
EmvHandler.onSetPinInputResponse.  
public void onCardHolderInputPin (boolean isOnlinePin, int leftTimes);  
Parameters:  
Parameter Description  
isOnlinePin Is online password  
leftTimes Enter the remaining number of times for the offline PIN  
Return Value: None  
4.6.7 onCertVerify  
EmvProcess executing the method of confirming documents, later to be called  
EmvHandler.onSetCertVerifyResponse.  
public void onCertVerify (String certName, String certInfo);  
Parameters:  
Parameter Description  
certName the name of your ID  
certInfo identity information  
Return Value: None  
4.6.8 onReadCardAgain  
EmvProcess executing the method, Callback the second read card .(When host response the script with  
contactless EMV transaction.) The application should re-search contactless card, then call  
EmvHandler.onSetReadCardAgainResponse to notify EMV continue procress.  
public void onReadCardAgain();  
Return Value: None  
Confidential 203  
| Parameter | Description |  
| --- | --- |  
| cardNo | card number |  
| Parameter | Description |  
| --- | --- |  
| isOnlinePin | Is online password |  
| leftTimes | Enter the remaining number of times for the offline PIN |  
| Parameter | Description |  
| --- | --- |  
| certName | the name of your ID |  
| certInfo | identity information |

## Página 204

SmartPos API Reference Manual: 09/08/21  
Note: for amex contactless, the method will called by kernel, when process case “please see phone”  
4.6.9 onOnlineProc  
EmvProcess executing the method, means EMV kernel request online process, Then the application  
should call method getTlv to get the tags, then send request message to the host. After host response,  
the application should call EmvHandler.onSetOnlineProcResponse to notify the EMV kernel to do the  
second auth.  
public void onOnlineProc ();  
Parameters: None  
Return Value: None  
4.6.10 onPrompt  
EmvProcess executing the method, notify the application prompt information to the user, Then the  
application should call EmvHandler. onPromptResponse to notify the EMV kernel to continue the flow.  
public void onPrompt(PromptEnum prompt);  
Parameters:  
Parameters Description  
prompt enum  
PromptEnum  
enum Description  
APP\_SELECTION\_IS\_NOT\_ACCEPTED Application is not accepted, please try again  
OFFLINE\_PIN\_INCORRECT\_TRY\_AGAIN Offline pin incorrect, please try again  
OFFLINE\_PIN\_INCORRECT Offline pin incorrect  
OFFLINE\_PIN\_CORRECT Offline pin correct  
Return Value: None  
4.6.11 onRemoveCard  
Confidential 204  
| Parameters | Description |  
| --- | --- |  
| prompt | enum |  
| enum | Description |  
| --- | --- |  
| APP\_SELECTION\_IS\_NOT\_ACCEPTED | Application is not accepted, please try again |  
| OFFLINE\_PIN\_INCORRECT\_TRY\_AGAIN | Offline pin incorrect, please try again |  
| OFFLINE\_PIN\_INCORRECT | Offline pin incorrect |  
| OFFLINE\_PIN\_CORRECT | Offline pin correct |

## Página 205

SmartPos API Reference Manual: 09/08/21  
EmvProcess executing the method, notify the application contactless card can be remove from the card  
reader, Then call EmvHandler. onSetRemoveCardResponse to notify the EMV kernel to continue the  
flow.  
public void onRemoveCard ();  
Parameters: None  
Return Value: None  
4.6.12 onFinish  
EmvProcess executing the method, means all the EMV flow is finish. The retcode will indicate the EMV  
transaction result.  
public void onFinish (int retCode, EmvProcessResultEntity entity);  
Parameters:  
Parameter Description  
retCode Enter the result :  
SdkResult.Success success  
SdkResult.Fail failure  
Other return results please refer to the EMV class table 3.7 or consult  
Appendix 5  
entity When retCode == SdkResult.Success return data  
Otherwise return null  
EmvProcessResultEntity  
Attributes Description  
byte [] scriptResult Script execution results  
List EMVlog Cards Blog List  
byte [] ecBalance Electronic cash balance  
EmvCardLogEntity  
Attributes Description  
boolean isAmtExist Whether the amount of presence  
String amt Amount of money  
boolean isOtherAmtExist Whether the existence of other  
String otherAmt Other Amount  
boolean isDateExist Date of the transaction if there are  
String transDate transaction date  
Confidential 205  
| Parameter | Description |  
| --- | --- |  
| retCode | Enter the result : SdkResult.Success success SdkResult.Fail failure Other return results please refer to the EMV class table 3.7 or consult Appendix 5 |  
| entity | When retCode == SdkResult.Success return data Otherwise return null |  
| Attributes | Description |  
| --- | --- |  
| byte [] scriptResult | Script execution results |  
| List EMVlog | Cards Blog List |  
| byte [] ecBalance | Electronic cash balance |  
| Attributes | Description |  
| --- | --- |  
| boolean isAmtExist | Whether the amount of presence |  
| String amt | Amount of money |  
| boolean isOtherAmtExist | Whether the existence of other |  
| String otherAmt | Other Amount |  
| boolean isDateExist | Date of the transaction if there are |  
| String transDate | transaction date |

## Página 206

SmartPos API Reference Manual: 09/08/21  
boolean isTimeExist The existence of transactions  
String transTime transaction hour  
boolean isCntCodeExist Whether there is a country code  
String cntCode country code  
boolean isCurExist Currency code if there  
String curCode Currency code  
boolean isAtcExist The existence of the transaction counter  
String atc Transaction Counter  
boolean is9CExist 9c transaction type whether there  
String serveType Transaction Type  
boolean isMerNameExist The existence of a business name  
String merName Business Name  
Return Value: None  
4.7 OnAppOperatListener  
Responsible for managing the apk install and uninstall result callback.  
4.7.1 onOperatResult  
Executing the installApp, after uninstallApp method is executed, the callback print the results.  
public void onOperatResult (int result);  
Parameters:  
Parameter Description  
result Enter the result :  
SdkResult.Success success  
SdkResult.Fail failure  
Other error code, please refer to Appendix  
Return Value: None  
4.8 OnUsbSerialReadListener  
Responsible for receiving the usb serial port data.  
Confidential 206  
| boolean isTimeExist | The existence of transactions |  
| --- | --- |  
| String transTime | transaction hour |  
| boolean isCntCodeExist | Whether there is a country code |  
| String cntCode | country code |  
| boolean isCurExist | Currency code if there |  
| String curCode | Currency code |  
| boolean isAtcExist | The existence of the transaction counter |  
| String atc | Transaction Counter |  
| boolean is9CExist | 9c transaction type whether there |  
| String serveType | Transaction Type |  
| boolean isMerNameExist | The existence of a business name |  
| String merName | Business Name |  
| Parameter | Description |  
| --- | --- |  
| result | Enter the result : SdkResult.Success success SdkResult.Fail failure Other error code, please refer to Appendix |

## Página 207

SmartPos API Reference Manual: 09/08/21  
4.8.1 onReadResult  
Executing the open method, if pos receive the data from usb serial port , this callback will be triggered.  
UsbSerial usbSerial = deviceEngine. getUsbSerial ();  
usbSerial.open(UsbSerialCfgEntity entity, OnUsbSerialReadListener listener);  
public void onReadResult(byte[] data);  
Parameters:  
Parameter Description  
data The Data receive from the usb serial port.  
Return Value: None  
Appendix  
Return Value Description  
public class SdkResult {  
public final static int Success = 0;  
public final static int Fail = -1;  
public final static int Param\_In\_Invalid = -2;  
public final static int TimeOut = -3;  
/ \* Device not signed \* /  
public final static int Device\_Not\_Ready = -4;  
// ---- Printer Error -----  
private final static int Printer\_Base\_Error = -1000;  
/ \*\* \* Print failed /  
public final static int Printer\_Print\_Fail = Printer\_Base\_Error -1;  
/ \* Failed to set string buffer \* /  
public final static int Printer\_AddPrnStr\_Fail = Printer\_Base\_Error -2;  
/ \*\* \* Set picture buffer failure /  
public final static int Printer\_AddImg\_Fail = Printer\_Base\_Error -3;  
/ \*\* \* Printer Busy /  
Confidential 207  
| Parameter | Description |  
| --- | --- |  
| data | The Data receive from the usb serial port. |

## Página 208

SmartPos API Reference Manual: 09/08/21  
public final static int Printer\_Busy = Printer\_Base\_Error - 4;  
/ \*\* \* The printer is out of paper /  
public final static int Printer\_PaperLack = Printer\_Base\_Error - 5;  
/ \*\* \* Wrong packet format print /  
public final static int Printer\_Wrong\_Package = Printer\_Base\_Error - 6;  
/ \*\* \* Printer Fault /  
public final static int Printer\_Fault = Printer\_Base\_Error - 7;  
/ \*\* \* Printer overheating /  
public final static int Printer\_TooHot = Printer\_Base\_Error - 8;  
/ \*\* \* Print the unfinished /  
public final static int Printer\_UnFinished = Printer\_Base\_Error - 9;  
/ \*\* Other exception error \* /  
public final static int Printer\_Other\_Error = Printer\_Base\_Error-999;  
// ---- Scanner Error -----  
private final static int Scanner\_Base\_Error = -2000;  
/ \*\* \* Button to exit the user /  
public final static int Scanner\_Customer\_Exit = Scanner\_Base\_Error-1;  
/ \*\* Other exception error \* /  
public final static int Scanner\_Other\_Error = Scanner\_Base\_Error-999;  
// ---- SerialPort Error -----  
private final static int SerialPort\_Base\_Error = -4000;  
/ \*\* \* Serial connection failure /  
public final static int SerialPort\_Connect\_Fail = SerialPort\_Base\_Error - 1;  
/ \*\* \* Serial data transmission failure /  
public final static int SerialPort\_Send\_Fail = SerialPort\_Base\_Error - 2;  
/ \*\* Fd error \* /  
public final static int SerialPort\_Fd\_Error = SerialPort\_Base\_Error - 3;  
/ \*\* \* Unopened serial /  
public final static int SerialPort\_Port\_Not\_Open = SerialPort\_Base\_Error - 4;  
/ \*\* \* Serial scission failure /  
public final static int SerialPort\_DisConnect\_Fail = SerialPort\_Base\_Error - 5;  
Confidential 208

## Página 209

SmartPos API Reference Manual: 09/08/21  
/ \*\* Transmit buffer is not empty (the remaining data to be transmitted) \* /  
public final static int SerialPort\_Sending\_Buf\_IsNot\_Null = SerialPort\_Base\_Error - 6;  
/ \*\* Invalid channel number \* /  
public final static int SerialPort\_Invalid\_Channel = SerialPort\_Base\_Error - 7;  
/ \*\* Channel is not open and no communication with any physical port \* /  
public final static int SerialPort\_Channel\_Isnot\_Open = SerialPort\_Base\_Error - 8;  
/ \*\* Transmit buffer error (continue 500ms at full state) \* /  
public final static int SerialPort\_Sending\_Buffer\_Error = SerialPort\_Base\_Error - 9;  
/ \*\* No available physical port \* /  
public final static int SerialPort\_No\_Available\_Ports = SerialPort\_Base\_Error - 10;  
/ \*\* Device enumeration and configuration process is not completed (USB DEV dedicated \* /  
public final static int SerialPort\_Conf\_Process\_Error = SerialPort\_Base\_Error - 11;  
/ \*\* Equipment de-energized and the host loses connection (USB DEV dedicated \* /  
public final static int SerialPort\_Device\_Lost\_Power = SerialPort\_Base\_Error - 12;  
/ \*\* From the host device and then plug plucking (USB DEV dedicated) \* /  
public final static int SerialPort\_Unplug\_Error = SerialPort\_Base\_Error - 13;  
/ \*\* Device is off (USBDEV dedicated) \* /  
public final static int SerialPort\_Device\_Is\_Off = SerialPort\_Base\_Error - 14;  
/ \*\* \* Data receive timeout /  
public final static int SerialPort\_Timeout\_Receiving\_Data = SerialPort\_Base\_Error - 15;  
/ \*\*\* Channel is being occupied by the system \* /  
public final static int SerialPort\_Channle\_Is\_Occupied = SerialPort\_Base\_Error - 16;  
/ \*\* Invalid communication Parameters, communication Parameters do not meet the rules for  
strings or data beyond the normal range. \* /  
public final static int SerialPort\_Invalid\_Communication\_Parameter = SerialPort\_Base\_Error - 17;  
/ \*\* USB to serial device mounted unsuccessful (the Return Value only FIDI USB to serial use) \* /  
public final static int SerialPort\_Usb\_Mounted\_Unsuccessful = SerialPort\_Base\_Error - 18;  
/ \*\* Usb to serial device error (only FTDI USB serial port using the Return Value of re-exports \* /  
public final static int SerialPort\_Reset\_Usb\_Error = SerialPort\_Base\_Error - 19;  
/ \*\* Device USB to serial chip traffic congestion (only FIDI USB serial adapter used in the Return  
Value) \* /  
Confidential 209

## Página 210

SmartPos API Reference Manual: 09/08/21  
public final static int SerialPort\_Devices\_Error = SerialPort\_Base\_Error - 20;  
/ \*\* Other exception error \* /  
public final static int SerialPort\_Other\_Error = SerialPort\_Base\_Error-999;  
// ---- MagCardReader Error -----  
private final static int MagCardReader\_Base\_Error = -5000;  
/ \*\* \* No credit card /  
public final static int MagCardReader\_No\_Swiped = MagCardReader\_Base\_Error -1;  
/ \*\* Other exception error \* /  
public final static int MagCardReader\_Other\_Error = MagCardReader\_Base\_Error -999;  
// ---- IccCardReader Error -----  
private final static int IccCardReader\_Base\_Error = -6000;  
public final static int IccCardReader\_Read\_CardType\_Error = IccCardReader\_Base\_Error-1;  
public final static int IccCardReader\_CardInit\_Error = IccCardReader\_Base\_Error-2;  
/ \*\* Other exception error \* /  
public final static int IccCardReader\_Other\_Error = IccCardReader\_Base\_Error-999;  
// ---- PinPad Error ----  
private final static int PinPad\_Base\_Error = -7000;  
/ \*\* \* Key does not exist /  
public final static int PinPad\_No\_Key\_Error = PinPad\_Base\_Error - 1;  
/ \*\* Wrong key index, the index is not within the Parameters range \* /  
public final static int PinPad\_KeyIdx\_Error = PinPad\_Base\_Error - 2;  
/ \*\* Did not enter PIN \* /  
public final static int PinPad\_No\_Pin\_Input = PinPad\_Base\_Error - 3;  
/ \*\* Cancel Enter PIN \* /  
public final static int PinPad\_Input\_Cancel = PinPad\_Base\_Error - 4;  
/ \*\* \* Key length wrong /  
public final static int PinPad\_Key\_Len\_Error = PinPad\_Base\_Error - 8;  
/ \*\* Enter PIN Timeout \* /  
public final static int PinPad\_Input\_Timeout = PinPad\_Base\_Error - 9;  
/ \*\* Open or close Pinpad failed \* /  
public final static int PinPad\_Open\_Or\_Close\_Error = PinPad\_Base\_Error - 10;  
Confidential 210

## Página 211

SmartPos API Reference Manual: 09/08/21  
/ \*\* Pinpad process error \* /  
public final static intPinPad\_Deal\_Error = PinPad\_Base\_Error - 11;  
/ \*\* Other exception error \* /  
public final static int PinPad\_Other\_Error = PinPad\_Base\_Error-999;  
// ---- EMVHandler Error ----  
private final static int EMVHandler\_Base\_Error = -8000;  
/ \*\* <Try other communication interface \* /  
public final static int EMV\_Other\_Interface = EMVHandler\_Base\_Error - 1;  
/ \*\* <Contactless transactions offline approved \* /  
public final static int EMV\_Qpboc\_Offline = EMVHandler\_Base\_Error - 2;  
/ \*\* <Contactless union pay online transaction \* /  
public final static int EMV\_Qpboc\_Online = EMVHandler\_Base\_Error - 3;  
/ \*\* <Contactless PBOC online transaction , Abolished \* /  
public final static int EMV\_Pboc\_Online = EMVHandler\_Base\_Error - 4;  
/ \*\* <Contactless MSD online transaction, Abolished \* /  
public final static int EMV\_MSD\_Online = EMVHandler\_Base\_Error - 5;  
/ \*\* <Offline electronic cash acceptance, Abolished \* /  
public final static int EMV\_Ec\_Accept = EMVHandler\_Base\_Error - 6;  
/ \*\* <contact transaction Offline approved \* /  
public final static int EMV\_Offline\_Accept = EMVHandler\_Base\_Error - 7;  
/ \*\* <Transaction card is removed \* /  
public final static int EMV\_Card\_Removed = EMVHandler\_Base\_Error -8;  
/ \*\* <Reader failed \* /  
public final static int EMV\_Command\_Fail = EMVHandler\_Base\_Error -9;  
/ \*\* <Card is Blocked \* /  
public final static int EMV\_Card\_Block = EMVHandler\_Base\_Error -10;  
/ \*\* <Parameters wrong \* /  
public final static int EMV\_PARA\_ERR = EMVHandler\_Base\_Error -11;  
/ \*\* <No common application \* /  
public final static int EMV\_Candidatelist\_Empty = EMVHandler\_Base\_Error -12;  
/ \*\* <Application locked \* /  
Confidential 211

## Página 212

SmartPos API Reference Manual: 09/08/21  
public final static int EMV\_App\_Block = EMVHandler\_Base\_Error -13;  
/ \*\* <Transaction fallback , need to swipe card\* /  
public final static int EMV\_FallBack = EMVHandler\_Base\_Error -14;  
/ \*\* <Data authentication has failed \* /  
public final static int EMV\_Auth\_Fail = EMVHandler\_Base\_Error -15;  
/ \*\* <Application has not yet entered into force \* /  
public final static int EMV\_App\_Ineffect = EMVHandler\_Base\_Error -16;  
/ \*\* <Application has expired \* /  
public final static int EMV\_App\_Expired = EMVHandler\_Base\_Error -17;  
/ \*\* <Cardholder verification failed \* /  
public final static int EMV\_Cvm\_Fail = EMVHandler\_Base\_Error -18;  
/ \*\* <\* Transactions should the online, Abolished /  
public final static int EMV\_Online = EMVHandler\_Base\_Error -19;  
/ \*\* <Cancel the transaction \* /  
public final static int EMV\_Cancel = EMVHandler\_Base\_Error -20;  
/ \*\* <Transaction online decline \* /  
public final static int EMV\_Declined = EMVHandler\_Base\_Error -21;  
/ \*\* <Issuer Authentication failed \* /  
public final static int EMV\_Arpc\_Fail = EMVHandler\_Base\_Error -22;  
/ \*\* <Issuer Script execution failed \* /  
public final static int EMV\_Script\_Fail = EMVHandler\_Base\_Error -23;  
/ \*\* <Applications are not accepted, you can re-select \* /  
public final static int EMV\_App\_NoAccept = EMVHandler\_Base\_Error -24;  
/ \*\* <Electronic cash offline decline \* /  
public final static int EMV\_Ec\_Decliend = EMVHandler\_Base\_Error -25;  
/ \*\* <Successful transaction, Issuer Authentication failed \* /  
public final static int EMV\_Sucess\_Arpc\_Fail = EMVHandler\_Base\_Error -26;  
/ \*\* <plese see phone\* /  
public final static int Emv\_Plz\_See\_Phone = EmvHandler\_Base\_Error - 27;  
/ \*\* < Transaction Terminate \* /  
public final static int Emv\_Terminate = EmvHandler\_Base\_Error - 28;  
Confidential 212

## Página 213

SmartPos API Reference Manual: 09/08/21  
/ \*\* < Transaction Communicate Timeout \* /  
public final static int Emv\_Communicate\_Timeout = EmvHandler\_Base\_Error - 29;  
/ \*\* < Use other card \* /  
public final static int Emv\_USE\_OTHER\_CARD = EmvHandler\_Base\_Error - 30;  
/ \*\* \* Other error exception /  
public final static int EMV\_Other\_Error = EMVHandler\_Base\_Error -999;  
// ---- CardHandler Error -----  
// ---- ~ -10000 -19900 Allocated to card manipulation  
// ---- -10.1 Thousand representatives contact CPU card -10200-- non-contact CPU card supports  
a total of 99 kinds of card types  
private final static int CardHandler\_Base\_Error = -10000;  
// ---- Contactless IC card return code segment  
public final static int Icc\_Base\_Error = CardHandler\_Base\_Error -100;  
/ \*\* Transaction card dialed \* /  
public final static int Icc\_PullOut\_Card = Icc\_Base\_Error - 1;  
/ \*\* Parity error \* /  
public final static int Icc\_Parity\_Err = Icc\_Base\_Error - 2;  
/ \*\* Select the channel error \* /  
public final static int Icc\_Channel\_Err = Icc\_Base\_Error - 3;  
/ \*\* Transmit data too long (LC) \* /  
public final static int Icc\_Data\_Len\_TooLong = Icc\_Base\_Error - 4;  
/ \*\* Card Error protocol (T = 0 or not T = 1) \* /  
public final static int Icc\_Protocol\_Err = Icc\_Base\_Error - 5;  
/ \*\* \* Not reset the card /  
public final static int Icc\_No\_Reset\_Card = Icc\_Base\_Error - 6;  
/ \*\* The dead can not communicate or \* /  
public final static int Icc\_Not\_Call = Icc\_Base\_Error - 7;  
/ \*\* Other exception error \* /  
public final static int Icc\_Other\_Error = Icc\_Base\_Error - 99;  
// ---- Contactless IC card return code segment  
private final static int Picc\_Base\_Error = CardHandler\_Base\_Error -200;  
Confidential 213

## Página 214

SmartPos API Reference Manual: 09/08/21  
/ \*\* \* RF module is not turned on /  
public final static int Picc\_Not\_Open = Picc\_Base\_Error - 1;  
/ \*\* Not find the card (the sensor area no specific type of card) \* /  
public final static int Picc\_Not\_Searched\_Card = Picc\_Base\_Error - 2;  
/ \*\* Card induction area too (there is a communication conflict) \* /  
public final static int Picc\_Card\_Too\_Many = Picc\_Base\_Error - 3;  
/ \*\* Protocol error (data in violation of the agreement appears response card) \* /  
public final static int Picc\_Protocol\_Data\_Err = Picc\_Base\_Error - 4;  
/ \*\* \* Card not active /  
public final static int Picc\_Card\_No\_Activation = Picc\_Base\_Error - 5;  
/ \*\* \* Conflict Doka /  
public final static int Picc\_Muti\_Card\_Err = Picc\_Base\_Error - 6;  
/ \*\* Protocol error \* /  
public final static int Picc\_Protocol\_Err = Picc\_Base\_Error - 7;  
/ \*\* Communications transmission error \* /  
public final static int Picc\_Io\_Err = Picc\_Base\_Error - 8;  
/ \*\* \* Card is still the sensor area /  
public final static int Picc\_Card\_Sense\_Err = Picc\_Base\_Error - 9;  
/ \*\* Card status error (such as A / B card calling card interface M1, or M1 card call  
PiccIsoCommand Interface) \* /  
public final static int Picc\_Card\_Status\_Err = Picc\_Base\_Error - 10;  
/ \*\* Interface chip does not exist or abnormal \* /  
public final static int Picc\_Not\_Call = Picc\_Base\_Error - 11;  
/ \*\* \* Other error exception /  
public final static int Picc\_Other\_Error = Picc\_Base\_Error - 99;  
/ \*\* M1 card section \* /  
public final static int M1Card\_Base\_Error = CardHandler\_Base\_Error -300;  
/ \*\* M1 card authentication failed \* /  
public final static int M1Card\_Verify\_Err = M1Card\_Base\_Error - 1;  
/ \*\* \* Sector unauthenticated /  
public final static int M1Card\_Fan\_Not\_Verify = M1Card\_Base\_Error - 2;  
Confidential 214

## Página 215

SmartPos API Reference Manual: 09/08/21  
/ \*\* Numeric data block format is wrong \* /  
public final static int M1Card\_Data\_Block\_Err = M1Card\_Base\_Error - 3;  
/ \*\* \* Module unopened /  
public final static int M1Card\_Not\_Open = M1Card\_Base\_Error - 4;  
/ \*\* \* Card not active /  
public final static int M1Card\_Card\_Not\_Activation = M1Card\_Base\_Error - 5;  
/ \*\* Wrong type of card operations for operateBlock the Senate operType Check \* /  
public final static int M1Card\_Card\_OperType\_Error = M1Card\_Base\_Error - 6;  
/ \*\* \* Other error exception /  
public final static int M1Card\_Other\_Error = M1Card\_Base\_Error - 99;  
//---- -20000 - 21000 Platform  
private final static int Platform\_Base\_Error = -20000;  
//20100-20199 install  
public final static int Platform\_Install\_Base\_Error = Platform\_Base\_Error - 100;  
/\*\* the package is already installed.\*/  
public final static int Platform\_Install\_Already\_Exists = Platform\_Install\_Base\_Error - 1;  
/\*\*the package archive file is invalid\*/  
public final static int Platform\_Install\_Invalid\_Apk = Platform\_Install\_Base\_Error - 2;  
/\*\*the URI passed in is invalid.\*/  
public final static int Platform\_Install\_Invalid\_Uri = Platform\_Install\_Base\_Error - 3;  
/\*\*the package manager service found that the device didn't have enough storage space to install  
the app.\*/  
public final static int Platform\_Install\_Insufficient\_Storage = Platform\_Install\_Base\_Error - 4;  
/\*\*package is already installed with the same name.\*/  
Confidential 215

## Página 216

SmartPos API Reference Manual: 09/08/21  
public final static int Platform\_Install\_Duplicate\_Package = Platform\_Install\_Base\_Error - 5;  
/\*\*the requested shared user does not exist\*/  
public final static int Platform\_Install\_No\_Shared\_User = Platform\_Install\_Base\_Error - 6;  
/\*\*a previously installed package of the same name has a different signature than the new  
package (and the old package's data was not removed).\*/  
public final static int Platform\_Install\_Update\_Incompatible = Platform\_Install\_Base\_Error - 7;  
/\*\*the new package is requested a shared user which is already installed on the device and does  
not have matchingsignature\*/  
public final static int Platform\_Install\_Shared\_User\_Incompatible = Platform\_Install\_Base\_Error -  
8;  
/\*\*the new package uses a shared library that is not available\*/  
public final static int Platform\_Install\_Missing\_Shared\_Library = Platform\_Install\_Base\_Error - 9;  
/\*\*the new package replace failed, case the delete failed.\*/  
public final static int Platform\_Install\_Replace\_Delete\_Failed = Platform\_Install\_Base\_Error - 10;  
/\*\*the new package failed while optimizing and validating its dex files, either because there was  
not enough storage or the validation failed\*/  
public final static int Platform\_Install\_Dexopt = Platform\_Install\_Base\_Error - 11;  
//20200-20299 uninstall  
public final static int Platform\_Uninstall\_Base\_Error = Platform\_Base\_Error - 200;  
/\*\*the system failed to uninstall the apk for an unspecified reason\*/  
public final static int Platform\_Uninstall\_Internal\_Error = Platform\_Uninstall\_Base\_Error - 1;  
/\*\*the system failed to uninstall the apk because it is the active DevicePolicy manager.\*/  
Confidential 216

## Página 217

SmartPos API Reference Manual: 09/08/21  
public final static int Platform\_Uninstall\_Device\_Policy\_Manager =  
Platform\_Uninstall\_Base\_Error - 2;  
/\*\*the system failed to uninstall the apk since the user is restricted\*/  
public final static int Platform\_Uninstall\_User\_Restricted = Platform\_Uninstall\_Base\_Error - 3;  
/\*\*the system failed to uninstall the apk because a profile or device owner has marked the  
package as uninstallable\*/  
public final static int Platform\_Uninstall\_Owner\_Blocked = Platform\_Uninstall\_Base\_Error - 4;  
/\*\*abort the uninstall\*/  
public final static int Platform\_Uninstall\_Aborted = Platform\_Uninstall\_Base\_Error - 5;  
//20300-20399 update logo & animation  
public final static int Platform\_Update\_Base\_Error = Platform\_Base\_Error - 300;  
/\*\*does not match, can not process\*/  
public final static int Platform\_Update\_No\_Match = Platform\_Update\_Base\_Error - 1;  
/\*\*update failed\*/  
public final static int Platform\_Update\_Failed = Platform\_Update\_Base\_Error - 2;  
}  
Confidential 217
