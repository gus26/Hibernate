package com.cursos_online;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.cursos_online.entidades.Curso;
import com.cursos_online.entidades.Estudiante;

public class Main {
	final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure() // configures settings from hibernate.cfg.xml
			.build();
	
	static SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
	

	public static void main(String[] args) {
		Curso cur1 = new Curso("Fundamentos de Java");
		Curso cur2 = new Curso("Hibernate para principiantes");
		
		
		ingresarCurso(cur1);
		ingresarCurso(cur2);
		
		
		
		
		
		Estudiante est1 = new Estudiante("gustavo", "jimenez");
		Estudiante est2 = new Estudiante("serena", "reale");
		Estudiante est3 = new Estudiante("gustavo", "martinez");
		
		
		
		
		
		ingresarEstudiante(est1);
		ingresarEstudiante(est2);
		ingresarEstudiante(est3);
		
		
		modificaCurso(1,"redes I");
		eliminarCurso(2);
		eliminarEstudiante(3);
		modificaEstudiante(5,"gustavo","martinez");
		
		
		List<Curso> cursos= getCursos();
		for(Curso temp:cursos) {
			System.out.println(temp);
		}
		
		List <Estudiante> estudiantes=getEstudiantesPorNombre("gustavo");
		for(Estudiante e :  estudiantes) {
		System.out.println(e);
	}
	}
	
	static void modificaCurso(int id,String nombre) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Curso curso=(Curso)session.get(Curso.class,id);
		curso.setDescripcion(nombre);
		session.update(curso);
		session.getTransaction().commit();
		session.close();
}
	
	
	static void eliminarCurso(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Curso curso=(Curso)session.get(Curso.class,id);
		session.delete(curso);
		session.getTransaction().commit();
		session.close();
	}
	
	static void modificaEstudiante(int id,String nombre,String apellido) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Estudiante estudiantes = (Estudiante)session.get(Estudiante.class,id);
		estudiantes.setNombre(nombre);
		estudiantes.setApellido(apellido);
		session.update(estudiantes);
		session.getTransaction().commit();
		session.close();
		
	}
	
	static void eliminarEstudiante(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Estudiante estudiantes = (Estudiante)session.get(Estudiante.class,id);
		session.delete(estudiantes);
		session.getTransaction().commit();
		session.close();
	}
	
	
	
	
	
static List<Estudiante> getEstudiantesPorNombre(String nombre) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Estudiante where nombre=:nombre");
		query.setParameter("nombre",nombre);
		
		List<Estudiante>estudiantes= (List<Estudiante>) query.getResultList();
		return estudiantes;
		
	}
		
	static List<Curso>getCursos(){
		Session session = sessionFactory.openSession();
		List<Curso>cursos= session.createQuery("from Curso" , Curso.class).list();
		return cursos;
		
	}
	
	
	static void ingresarCurso(Curso curso) {
		
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	session.save(curso);
	
	session.getTransaction().commit();
	session.close();

}
	static void ingresarEstudiante(Estudiante estudiante) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(estudiante);
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println(estudiante.getId());
}
}


		