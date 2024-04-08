# TCC
Trabalho final de curso 

O que iremos fazer?
	telemetria do satelite cubesat, coleta de dados e armazenamento deles-> a coleta deve ser protegida, cript. de dados
	
Protocolos:
	AX.25
	CCSDS *melhor

Softwares mais utilizados para transferência de dados por telemetria:
	*OpenTelemetry - framework de observabilidade de código aberto que permite coletar, processar e transmitir 
			dados de telemetria em um formato unificado.
			Oferece SDKs, APIs e ferramentas independentes de fornecedor para enviar dados para qualquer 
			backend de observabilidade para análise.
			Padrão dominante em aplicações nativas da nuvem.

			https://opentelemetry.io/

	DATA-LOGGER - não é um software específico, dispositivo usado para coletar dados de telemetria.
			Grava leituras de canais ao longo do tempo e transfere esses dados para análise no PC.
			Comum em sistemas que precisam monitorar ativos e tomar decisões com base nos resultados.

	OpMon - especializado em medir estados de equipamentos usados para sustentar processos de negócios.

	elastic.co
	eximia.co
	smartinstec.com.br
	opservices.com.br
	produttivo.com.br

Possíveis tabelas:
	cubesats - id, dataFabricacao, nome
	estado do cubesat - localização, ativo
	dados recebidos - umidade, temperatura, pressão
	transporte - ip, chegada, saída
	
