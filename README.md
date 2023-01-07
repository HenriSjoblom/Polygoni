# Polygoni

Ohjelma lukee pisteet.txt ja polygoni.txt tiedostot ja selvittää ovatko pisteet polygonin sisäpuolella, ulkopuolella vai reunaviivan päällä,
jonka jälkeen ohjelma kirjoittaa raportin selvityksestä tiedostoon selvitys.txt. Ohjelma on tehty Test Driven Development (TDD) menetelmää käyttäen. 
Tiedostojen sijaintina oletetaan olevan temp-kansio.

Ohjelma hyödyntää pisteiden sijainnin selvityksessä Haon algoritmia. 
Lähde: https://www.researchgate.net/publication/328261365_Optimal_Reliable_Point-in-Polygon_Test_and_Differential_Coding_Boolean_Operations_on_Polygons

Ohjelman testaamista varten on junit testejä, joilla ohjelman eri osia voi testata. Polygonia voi testata tiedoston kanssa tai ilman tiedostoa.

Polygoni ja pisteet -tiedostojen rakenne oletetaan olevan samanlainen. Ohjelma luo polygonin annettujen pisteiden perusteella. Molemmat tiedostot oletetaan muodostuvan riveistä, joissa on yhden pisteen koordinaatit ja X ja Y koordinaatti on erotettu pilkulla. Esimerkiksi rivi voisi olla "2, 4". Ohjelma hyväksyy ja toimii myös liukuluvuilla.
