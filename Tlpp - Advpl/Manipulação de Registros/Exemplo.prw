#Include "Protheus.ch"
#Include "Totvs.ch"
#Include "TbiConn.ch"

/*/-------------------------------------------------------------------------
- Programa: UTESTE01
- Autor: Kauan Santana
- Data: 02/04/2024
--------------------------------------------------------------------------/*/
User Function UTESTE01()

	Local cCodEmp := "01"
	Local cCodFil := "040101"

	Reset Environment
	If RpcSetEnv(cCodEmp,cCodFil,,,"FIS")
		fDupliReg()
		If !FWGetRunSchedule()
			RpcClearEnv()
		EndIf
	Else
		FWLogMsg("ERROR",, "UTESTE01",,,, "A empresa informada não possui cadastro na base de dados do Protheus, Empresa : " + cCodEmp + " filial : " + cCodFil + " Função : ",,,)
	EndIf

Return( Nil )

/*/---------------------------------------------------------------------------
- Programa: fDupliReg
- Autor: Kauan Santana
- Data: 02/04/2024
- Descrição: Função para manipular registros
----------------------------------------------------------------------------/*/
Static Function fDupliReg()

	Local aArea 	:= FwGetArea()
	Local cQrySF1	:= ""
	Local cQrySD1	:= ""
	Local cQrySF3	:= ""
	Local cQrySFT	:= ""
	Local cAlQryF1	:= ""
	Local cAlQryD1	:= ""
	Local cAlQryF3	:= ""
	Local cAlQryFT	:= ""
	Local nX		:= 1
	Local nPosFil	:= 1
	Local nPosDoc	:= 2
	Local nPosSer	:= 3
	Local nPosChv	:= 4
	Local aData		:= {}
	Local aTabEReg	:= {}
	Local aAltDados	:= {}
	Local aDelDados	:= {}
	Local cLocFile	:= "c:\temp5\duplicar.csv" //=> Arquivo csv com os dados a serem duplicados, devem ser separados por ponto e virgula e sem cabeçalho

	aData := fGetDados( cLocFile )

	For nX := 1 to Len( aData )

		cQrySF1 := "	SELECT 													" + CRLF
		cQrySF1 += "	    F2.R_E_C_N_O_ AS SF2_RECNO							" + CRLF
		cQrySF1 += "	FROM "+ retsqlname("SF2") +"	   F2					" + CRLF
		cQrySF1 += "	WHERE F2.D_E_L_E_T_  = ' '								" + CRLF						
		cQrySF1 += "		AND F2_DOC    	  = '" + aData[nX][nPosDoc] + "'	" + CRLF		
		cQrySF1 += "		AND F2_SERIE      = '" + aData[nX][nPosSer] + "' 	" + CRLF		
		cQrySF1 += "		AND F2_FILIAL     = '040101'						" + CRLF

		cQrySD1 := "	SELECT 													" + CRLF
		cQrySD1 += "	    D2.R_E_C_N_O_ AS SD2_RECNO							" + CRLF
		cQrySD1 += "	FROM "+ retsqlname("SD2") +"	   D2					" + CRLF
		cQrySD1 += "	WHERE D2.D_E_L_E_T_  = ' '								" + CRLF
		cQrySD1 += "		AND D2_DOC    	  = '" + aData[nX][nPosDoc] + "'	" + CRLF
		cQrySD1 += "		AND D2_SERIE      = '" + aData[nX][nPosSer] + "' 	" + CRLF
		cQrySD1 += "		AND D2_FILIAL     = '040101'						" + CRLF

		cQrySF3 := "	SELECT 													" + CRLF
		cQrySF3 += "	    F3.R_E_C_N_O_ AS SF3_RECNO							" + CRLF
		cQrySF3 += "	FROM "+ retsqlname("SF3") +"	   F3					" + CRLF
		cQrySF3 += "	WHERE F3.D_E_L_E_T_  = ' '								" + CRLF
		cQrySF3 += "		AND F3_NFISCAL    = '" + aData[nX][nPosDoc] + "'	" + CRLF
		cQrySF3 += "		AND F3_SERIE      = '" + aData[nX][nPosSer] + "' 	" + CRLF
		cQrySF3 += "		AND F3_FILIAL     = '040101'						" + CRLF

		cQrySFT := "	SELECT 													" + CRLF
		cQrySFT += "	    FT.R_E_C_N_O_ AS SFT_RECNO							" + CRLF
		cQrySFT += "	FROM "+ retsqlname("SFT") +"	   FT					" + CRLF
		cQrySFT += "	WHERE FT.D_E_L_E_T_  = ' '								" + CRLF
		cQrySFT += "		AND FT_NFISCAL    = '" + aData[nX][nPosDoc] + "'	" + CRLF
		cQrySFT += "		AND FT_SERIE      = '" + aData[nX][nPosSer] + "' 	" + CRLF
		cQrySFT += "		AND FT_FILIAL     = '040101'						" + CRLF
		cQrySFT += "		AND FT.FT_TIPOMOV = 'S'								" + CRLF

		cAlQryF1 := MPSysOpenQuery( cQrySF1 )
		cAlQryD1 := MPSysOpenQuery( cQrySD1 )
		cAlQryF3 := MPSysOpenQuery( cQrySF3 )
		cAlQryFT := MPSysOpenQuery( cQrySFT )

		While !(cAlQryF1)->(Eof())

			If !Empty((cAlQryF1)->SF2_RECNO)
				AADD(aTabEReg ,{"SF2",(cAlQryF1)->SF2_RECNO})
			EndIf
			
			(cAlQryF1)->(DbSkip())
		EndDO

		While !(cAlQryD1)->(Eof())

			If !Empty((cAlQryD1)->SD2_RECNO)
				AADD(aTabEReg ,{"SD2",(cAlQryD1)->SD2_RECNO})
			EndIf
			
			(cAlQryD1)->(DbSkip())
		EndDO

		While !(cAlQryF3)->(Eof())

			If !Empty((cAlQryF3)->SF3_RECNO)
				AADD(aTabEReg ,{"SF3",(cAlQryF3)->SF3_RECNO})
			EndIf
			
			(cAlQryF3)->(DbSkip())
		EndDO

		While !(cAlQryFT)->(Eof())

			If !Empty((cAlQryFT)->SFT_RECNO)
				AADD(aTabEReg ,{"SFT",(cAlQryFT)->SFT_RECNO})
			EndIf
			
			(cAlQryFT)->(DbSkip())
		EndDO

		AADD(aAltDados,{"F2_FILIAL",aData[nX][nPosFil]})
		AADD(aAltDados,{"D2_FILIAL",aData[nX][nPosFil]})
		AADD(aAltDados,{"F3_FILIAL",aData[nX][nPosFil]})
		AADD(aAltDados,{"FT_FILIAL",aData[nX][nPosFil]})
		AADD(aAltDados,{"F2_CHVNFE",aData[nX][nPosChv]})
		AADD(aAltDados,{"F3_CHVNFE",aData[nX][nPosChv]})
		AADD(aAltDados,{"FT_CHVNFE",aData[nX][nPosChv]})

		AADD(aDelDados,{"SD2","D2_MSUIDT"})

		EXECREG():ManipulaReg(aTabEReg,aAltDados,,.T.)

		aTabEReg  := {}
		aAltDados := {}
		aDelDados := {}

		If Select(cAlQryF1) > 0 .AND. !Empty(cAlQryF1)
			(cAlQryF1)->(DbCloseArea())
		EndIf

		If Select(cAlQryD1) > 0 .AND. !Empty(cAlQryD1)
			(cAlQryD1)->(DbCloseArea())
		EndIf

		If Select(cAlQryF3) > 0 .AND. !Empty(cAlQryF3)
			(cAlQryF3)->(DbCloseArea())
		EndIf

		If Select(cAlQryFT) > 0 .AND. !Empty(cAlQryFT)
			(cAlQryFT)->(DbCloseArea())
		EndIf
	
	Next

	FwRestArea(aArea)

Return( Nil )

/*/----------------------------------------------------------------------------
- Função: fGetDados
- Autor: Kauan Santana
- Data: 02/04/2024
- Descrição: Função para ler um arquivo csv e retornar um array com os dados.
------------------------------------------------------------------------------/*/
Static Function fGetDados( cLocFile )

	Local oFile		 := Nil
	Local aDados	 := {}
	Default cLocFile := ""

	oFile := FWFileReader():New(cLocFile)

	If oFile:Open()

		While oFile:HasLine()
			aAdd(aDados,Separa(oFile:GetLine(),";",.T.))
		EndDo

		oFile:Close()
	EndIf

Return( aDados )
