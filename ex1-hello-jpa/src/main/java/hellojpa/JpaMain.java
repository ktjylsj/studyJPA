package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args){
        // 웹 서버 실행 시 1회
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // == DB Connection ( 고객 요청 존재 시 만들었다가 쓰고 닫고, 쓰레드 공유 X )
        EntityManager em = emf.createEntityManager();

        // code 작성 위치
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setId(3L);
            member.setName("C");
            member.setRoleType(RoleType.GUEST);

            em.persist(member);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
