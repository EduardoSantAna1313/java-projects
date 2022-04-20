### Problemas
00. Como posso evitar o acoplamento entre o rementende de uma solicitação e seu receptor
00. Como permitir que mais que um objeto possa atender alguma requisição ?

### Solução
00. Definir uma cadeia de objetos onde cada um poderá reponder aquela solicitaçao ou enviar para o próximo objeto trata-la.
00. Quem faz a solicitação não precisa saber o tamanho da cadeira, ou mesmo como (por quem) ela será resolvida.


>> Evita acoplar o rementente de uma requisição ao seu destinatario ao dar a mais de um objeto a chance de servir a requisição. Compoẽm os objetos em cascata e passa a requisição pela corrente até que um objeto a sirva
GOF

