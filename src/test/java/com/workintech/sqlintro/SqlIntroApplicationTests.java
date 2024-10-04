package com.workintech.sqlintro;

import com.workintech.sqlintro.repository.OgrenciRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(ResultAnalyzer.class)
class SqlIntroApplicationTests {

	private OgrenciRepository ogrenciRepository;

	@Autowired
	public SqlIntroApplicationTests(OgrenciRepository ogrenciRepository) {
		this.ogrenciRepository = ogrenciRepository;
	}

	@DisplayName("Öğrenci tablosundaki tüm kayıtları listeleyin.")
	@Test
	void getAllStudentTest(){
		assertEquals(10, ogrenciRepository.findAll().size());
		assertEquals("Hülya", ogrenciRepository.findAll().get(0).getAd());
	}

	@DisplayName("Öğrenci tablosundaki kız öğrencileri listeleyin.")
	@Test
	void getAllGirlsTest(){
		assertEquals(5, ogrenciRepository.findGirls().size());
		assertEquals("Hülya", ogrenciRepository.findAll().get(0).getAd());
	}

	@DisplayName("Öğrenci tablosunda kaydı bulunan sınıfların adını her sınıf bir kez görüntülenecek şekilde listeleyiniz")
	@Test
	void getAllStudentBySpecialColumnsTest(){
		assertEquals(ogrenciRepository.findAllClasses().size(), 6);
	}

	@DisplayName("Öğrenci tablosunda, 10A sınıfında olan kız öğrencileri listeleyiniz.")
	@Test
	void getFind10AGirlsTest(){
		assertEquals(1, ogrenciRepository.find10AGirls().size());
		assertEquals("Hülya", ogrenciRepository.find10AGirls().get(0).getAd());
		assertEquals("Yiğit", ogrenciRepository.find10AGirls().get(0).getSoyad());
	}

	@DisplayName("Öğrenci numarası 5 ile 10 arasında olan Kız öğrencileri listeleyiniz.")
	@Test
	void getFindGirlsWithOgrnoTest(){
		assertEquals(ogrenciRepository.findGirlsWithOgrno().size(), 3);
		assertEquals(ogrenciRepository.findGirlsWithOgrno().get(0).getAd(), "Betül");
	}

	@DisplayName("Öğrencileri adına göre sıralayınız (alfabetik)")
	@Test
	void findStudentsAlphabeticallyTest(){
		assertEquals(ogrenciRepository.findStudentsAlphabetically().size(), 10);
		assertEquals(ogrenciRepository.findStudentsAlphabetically().get(0).getAd(), "Betül");
		assertEquals(ogrenciRepository.findStudentsAlphabetically()
				.get(ogrenciRepository.findStudentsAlphabetically().size()-1).getAd(), "Sema");
	}

	@DisplayName("10A sınıfındaki öğrencileri okul numarasına göre azalan olarak sıralayınız.")
	@Test
	void find10AStudentsByOgrNo(){
		assertEquals(ogrenciRepository.find10AStudentsByOgrNo().size(), 1);
		assertEquals(ogrenciRepository.find10AStudentsByOgrNo().get(0).getAd(), "Hülya");
	}

	@DisplayName("Öğrenciler tablosundaki en genç öğrenciyi listeleyiniz.")
	@Test
	void findYoungestStudentTest(){
		assertEquals(ogrenciRepository.findYoungestStudent().getAd(), "Niyazi");
		assertEquals(ogrenciRepository.findYoungestStudent().getSoyad(), "Sevinç");
	}

	@DisplayName("Öğrenciler tablosundaki en yaşlı öğrenciyi listeleyiniz.")
	@Test
	void findElderStudentTest(){
		assertEquals(ogrenciRepository.findElderStudent().getAd(), "Kenan");
		assertEquals(ogrenciRepository.findElderStudent().getSoyad(), "Emin");
	}

	@DisplayName("İkinci harfi E olan kitapları listeleyiniz..")
	@Test
	void findBooksSecondLetterOfNTest(){
		assertEquals(ogrenciRepository.findStudentsSecondLetterOfN().size(), 5);
	}
}
