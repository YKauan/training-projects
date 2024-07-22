#include "Protheus.ch"
#Include "TbiConn.ch"
#Include "Totvs.ch"

/*/----------------------------------------------------------------------------
	- Programa: EXECREG
	- Autor: Kauan Santana
	- Data: 02/04/2024
	- Descricao: Classe para duplicar registros em qualquer tabela
    - Metodos:
        - ManipulaReg: Metodo responsavel por copiar ou alterar um registro
-------------------------------------------------------------------------------/*/
Class EXECREG

    //=> Metodos 
    Static Method ManipulaReg( ARECNOREG, ADATAINFO, lTipo )

EndClass

/*/---------------------------------------------------------------------------------------------
	- Programa: ManipulaReg
	- Autor: Kauan Santana
	- Data: 02/04/2024
	- Descricao: 
    - Parametros: Metodo responsavel por copiar ou alterar um registro
        - ARECNOREG: Array contendo tabela e numero do Registro ex: {"SA1",123456}
        - ADATAINFO: Array contendo campo e conteudo a serem alterados ex: {"A1_LOJA","01"}
        - ADATADEL: Array contendo os campos a serem deletados
        - lTipo: Indica se vai ser alteracao ou exclusao, sendo .T. inclusao, .F. alteracao
    Obs: O arquivo deve ser separado por ponto e virgula
-----------------------------------------------------------------------------------------------/*/
Method ManipulaReg( ARECNOREG, ADATAINFO, ADATADEL, lTipo) Class EXECREG
    
    Local aArea 	    := FwGetArea()
    Local aAreaAlias    := ""
    Local aCampos	    := {}
    Local aAltCampos    := {}
    Local aData         := {}
    Local aDelete       := {}
    Local nX            := 0
    Local nXData        := 0
    Local nXDel         := 0
    Local nXVaue        := 0
    Local nXMacro       := 0
    Local nXDel2        := 0
    Local nXAlias       := 0
    Local nXRec         := 0
    Local nPos          := 0
    Local nAux          := 0
    Local cReg          := ""   
    Local cIsEmpty      := ""
    Local cValue        := ""
    Local cAlias        := ""
    Local lAltMore      := .F.
    DEFAULT ARECNOREG   := {}
    DEFAULT ADATAINFO   := {}
    DEFAULT ADATADEL    := {}
    DEFAULT lTipo       := .F.

    For nXAlias := 1 To Len(ARECNOREG)

        cAlias     := ARECNOREG[nXAlias][1]
        aAreaAlias := ((cAlias))->(FwGetArea())

        aData := FWSX3Util():GetListFieldsStruct( (cAlias) , .T.)

        For nX := 1 To Len(aData)
            AADD( aCampos, aData[nX][1] )
        Next

        DBSelectArea((cAlias))
        (cAlias)->(DbGoTo(ARECNOREG[nXAlias][2]))

        aAltCampos := aClone(aCampos)
        For nXData := 1 To Len(ADATAINFO)
            nPos := aScanX(aCampos, {|x| AllTrim(Upper(x)) == ADATAINFO[nXData][1]})
            If nPos > 0
                aAltCampos[nPos] := ADATAINFO[nXData][2]
            EndIf
        Next

        If Len(ADATADEL) > 0
            For nXDel := 1 To Len(ADATADEL)
                nPos := aScanX(aCampos, {|x| AllTrim(Upper(x)) == ADATADEL[nXDel][2]})
                If nPos > 0 .AND. ADATADEL[nXDel][1] == cAlias
                    AADD(aDelete, nPos)
                EndIf
            Next
        EndIf

        For nXVaue := 1 To Len(aCampos)
            If RAT("_",aAltCampos[nXVaue]) > 0
                IF FieldPos(aCampos[nXVaue]) > 0 
                    cValue := (cAlias) + "->" + aCampos[nXVaue]
                    aAltCampos[nXVaue] := &cValue
                Else
                    AADD(aDelete, nXVaue)
                EndIf
            EndIf
        Next

        If Len(aDelete) > 0
            For nXDel2 := 1 To Len(aDelete)
                nAux++
                If nXDel2 >= 2
                    aDel(aAltCampos, aDelete[nXDel2] - (nAux - 1))
                    aDel(aCampos, aDelete[nXDel2] - (nAux - 1))
                Else
                    aDel(aAltCampos, aDelete[nXDel2])
                    aDel(aCampos, aDelete[nXDel2])
                EndIf
                If aDelete[nXDel2] == Len(aAltCampos)
                    nAux--
                EndIf
                aSize(aAltCampos, Len(aAltCampos) - 1)
                aSize(aCampos, Len(aCampos) - 1)
            Next
            nAux := 0
        EndIf

        If RecLock( (cAlias), lTipo )
            For nXMacro := 1 To Len(aAltCampos)
                cReg := (cAlias) + "->" + aCampos[nXMacro] + " := " + 'aAltCampos[nXMacro]'
                &cReg
            Next
            (cAlias)->(MsUnLock())
        EndIf

        aData      := {}
        aAltCampos := {}
        aDelete    := {}
        aCampos    := {}

        FwRestArea(aAreaAlias)
    Next

	FwRestArea(aArea)

Return( Nil )
