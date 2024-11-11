package TP1;

public class TesterConjunto {
	public static void main(String args[]) {
//Creacion e insercion
System.out.println("Creacion e insercion(Constructor, put, capacity, size, isEmpty)");
		Conjunto<Integer> ent = new ConjuntoArreglo<Integer>();
		ent.put(0);
		ent.put(1);
		ent.put(2);
		ent.put(3);
		ent.put(4);
		ent.put(5);
		ent.put(6);
		ent.put(7);
		ent.put(8);
		ent.put(9);
		System.out.println("Conjunto Numeros Enteros: "+ent.toString()+"Capacidad: "+ent.capacity()+" Elementos: "+ent.size()+" Esta Vacio?: "+ent.isEmpty());
		Conjunto<Integer> imp = new ConjuntoArreglo<Integer>();
		imp.put(1);
		imp.put(3);
		imp.put(5);
		imp.put(7);
		imp.put(9);
		System.out.println("Conjunto Numeros Impares: "+imp.toString()+"Capacidad: "+imp.capacity()+" Elementos: "+imp.size()+" Esta Vacio?: "+imp.isEmpty());
		Conjunto<Integer> par = new ConjuntoArreglo<Integer>();
		par.put(0);
		par.put(2);
		par.put(4);
		par.put(6);
		par.put(8);
		System.out.println("Conjunto Numeros Pares: "+par.toString()+"Capacidad: "+par.capacity()+" Elementos: "+par.size()+" Esta Vacio?: "+par.isEmpty());
		Conjunto<Integer> vacio = new ConjuntoArreglo<Integer>();
		System.out.println("Conjunto Vacio: "+vacio.toString()+"Capacidad: "+vacio.capacity()+" Elementos: "+vacio.size()+" Esta Vacio?: "+vacio.isEmpty());
//Pertenece
System.out.println("");
System.out.println("Prueba de pertenencia(pertenece)");
System.out.println("Caso 1: 1 en enteros");
System.out.println(ent.pertenece(1));
System.out.println("Caso 2: 1 en Impares");
System.out.println(imp.pertenece(1));
System.out.println("Caso 3: 1 en pares");
System.out.println(par.pertenece(1));

//Intercalado
System.out.println("");
System.out.println("Prueba de interseccion(B interseca A)");
System.out.println("Caso 1: impares en enteros");
System.out.println("Elementos en Comun: "+ent.interseccion(imp).toString());
System.out.println("Caso 2: enteros en pares");
System.out.println("Elementos en Comun: "+par.interseccion(ent).toString());
System.out.println("Caso 3: impares en pares");
System.out.println("Elementos en Comun: "+par.interseccion(imp).toString());
	}
}
