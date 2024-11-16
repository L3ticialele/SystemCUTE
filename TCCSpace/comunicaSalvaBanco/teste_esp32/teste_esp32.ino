// Definição das variáveis globais
float accX = 1.0;
float accY = 2.0;
float accZ = 3.0;
float angX = 45.0;
float angY = 4.0;
float angZ = 90.0;
float altitude = 100.0;
float bat = 12.5;
float iBat = 2.3;
float iSolar = 0.8;
float gas1 = 25.0;
float gas2 = 30.0;
float luz1 = 500.0;
float luz2 = 700.0;
float orvalho = 15.0;
float pressao = 1013.25;
float uv = 7.0;
float externo = 25.0;
float interno = 20.0;
float vBat = 12.8;
float vSolar = 13.5;
float umidade = 60.0;
float veloc = 10.0;
float wX = 0.5;
float wY = 1.0;
float wZ = 1.5;

// Variável para adicionar ao valor a cada loop
int valorAdicional = 1;

void setup() {
  Serial.begin(9600);
}

void loop() {
  // Adiciona o valor constante a cada variável
  accX += valorAdicional;
  accY += valorAdicional;
  accZ += valorAdicional;
  angX += valorAdicional;
  angY += valorAdicional;
  angZ += valorAdicional;
  altitude += valorAdicional;
  bat += valorAdicional;
  iBat += valorAdicional;
  iSolar += valorAdicional;
  gas1 += valorAdicional;
  gas2 += valorAdicional;
  luz1 += valorAdicional;
  luz2 += valorAdicional;
  orvalho += valorAdicional;
  pressao += valorAdicional;
  uv += valorAdicional;
  externo += valorAdicional;
  interno += valorAdicional;
  vBat += valorAdicional;
  vSolar += valorAdicional;
  umidade += valorAdicional;
  veloc += valorAdicional;
  wX += valorAdicional;
  wY += valorAdicional;
  wZ += valorAdicional;

  // Imprime os valores formatados
  Serial.print("accX:");
  Serial.print(accX);
  Serial.print(" accY:");
  Serial.print(accY);
  Serial.print(" accZ:");
  Serial.print(accZ);
  Serial.print(" angX:");
  Serial.print(angX);
  Serial.print(" angY:");
  Serial.print(angY);
  Serial.print(" angZ:");
  Serial.print(angZ);
  Serial.print(" altitude:");
  Serial.print(altitude);
  Serial.print(" bat:");
  Serial.print(bat);
  Serial.print(" iBat:");
  Serial.print(iBat);
  Serial.print(" iSolar:");
  Serial.print(iSolar);
  Serial.print(" gas1:");
  Serial.print(gas1);
  Serial.print(" gas2:");
  Serial.print(gas2);
  Serial.print(" luz1:");
  Serial.print(luz1);
  Serial.print(" luz2:");
  Serial.print(luz2);
  Serial.print(" orvalho:");
  Serial.print(orvalho);
  Serial.print(" pressao:");
  Serial.print(pressao);
  Serial.print(" uv:");
  Serial.print(uv);
  Serial.print(" externo:");
  Serial.print(externo);
  Serial.print(" interno:");
  Serial.print(interno);
  Serial.print(" vBat:");
  Serial.print(vBat);
  Serial.print(" vSolar:");
  Serial.print(vSolar);
  Serial.print(" umidade:");
  Serial.print(umidade);
  Serial.print(" veloc:");
  Serial.print(veloc);
  Serial.print(" wX:");
  Serial.print(wX);
  Serial.print(" wY:");
  Serial.print(wY);
  Serial.print(" wZ:");
  Serial.println(wZ);

  // Aguarda 1 segundo antes do próximo loop
  delay(1000);
}
