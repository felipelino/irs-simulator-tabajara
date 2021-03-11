
# Simulador simplificado de IRS em Portugal

Simulador extremamente simplificado de IRS em Portugal. 

**Não o considere como exato.** 

Dependendo da situação serve apenas para ter uma noção do quanto deve pagar de IRS ou terá de restituição.

# Build

## Pre-requisito

* Java JDK 1.8 ou superior
* Maven 3

## Comando

`mvn clean package`

# Como usar  

## Preparar os ficheiros

* **mensal.csv** : arquivo CSV no formato a seguir, deve ter cerca de 12 linhas uma para cada mês do ano.
 `valor_tributavel;retido_fonte;tsu_retido`
    
Exemplo:

    1500;217.50;0.00;JAN  
    1500;217.50;0.00;FEV  
    1500;217.50;0.00;MAR  
    1500;217.50;0.00;APR  
    3000;657.00;0.00;MAY  
    1500;217.50;0.00;JUN  
    1500;217.50;0.00;JUL  
    1500;217.50;0.00;AUG  
    1500;217.50;0.00;SEPT  
    1500;217.50;0.00;OCT  
    1500;217.50;0.00;NOV  
    3000;657.00;0.00;DEC  

*  **acoes.csv**: arquivo CSV no formato a seguir:
 `quantidade;valor_unit_dolar;cotacao_para_euro;operacao(VEST|BUY|SELL|PREVIOUS)`

Exemplo:

    50;17.84;0.88;VEST  
    30;12.04;0.88;PREVIOUS  
    40;50;0.88;SELL


* VEST: Significa que recebeste ações da empresa
* BUY: Significa que compraste ações
* SELL: Significa que vendeste ações
* PREVIOUS: Significa que são ações de anos anteriores que estão em seu poder

## Correr o comando

`java -jar ./target/irs-simulator-1.0.0-SNAPHOST [Single, Married1, Married2, RNH] full_path_to_mensal_CSV full_path_to_acoes.csv`  

O primeiro parametro diz respeito se a simulação é para solteiro, casado em que apenas 1 trabalha, ou casado com os 2 a trabalhar, ou ainda RNH (Residente Não-Habitual).

O regime de RNH aplica-se 20% de taxa de imposto sem deduções.

No caso de casado com os 2 a trabalhar o CSV deve conter os dados de ambos os conjuges.
Exemplo: `java -jar ./target/irs-simulator-1.0.0-SNAPHOST "Single" "c:\temp\mensal.csv" "c:\temp\acoes.csv"`

Resultado esperado 

    IRS Type....................:  Single
    Valor bruto total recebido..:  23862.82
    	 = Total bruto salario....:  21000.00
    	 + Total RSUs Vested......:  2862.82
    Deducao.......................: -4104.00
    
    Total sujeito a tributo.....:  19758.82
    Total do lucro de acoes.....:  0.00
    Valor total de IRS..........:  4436.48
    Valor de IRS retido na fonte: -3489.00
    IRS Total a pagar...........:  947.48

# Referencia

* https://www.montepio.org/ei/pessoal/impostos/como-calcular-o-irs/