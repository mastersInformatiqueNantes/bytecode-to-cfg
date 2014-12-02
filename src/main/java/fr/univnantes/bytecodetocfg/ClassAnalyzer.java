package fr.univnantes.bytecodetocfg;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.InstructionAdapter;

import fr.univnantes.controlflowgraph.Instruction;
import fr.univnantes.controlflowgraph.Node;

/*
 * Class visitor to detect when the analyzer reach
 * method to analyze
 * 
 * @see visitMethod
 * 
 */
public class ClassAnalyzer implements ClassVisitor{

	// Name fo the method to analyze
	private String methodName;
	
	private MethodAnalyzer methodAnalyser;
		
	public ClassAnalyzer(String methodToAnalyse){
		this.methodName = methodToAnalyse;
	}
	
	/*
	 * Method triggered before a method analyze begins 
	 * @see org.objectweb.asm.ClassVisitor#visitMethod(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String[])
	 */
	public MethodVisitor visitMethod(int arg0, String arg1, String arg2,
			String arg3, String[] arg4) {
		
//		System.out.println("visitMethod: " + arg0 + " # " + arg1 + " # " + arg2 + " # " + arg3 + " # " + arg4);
		
		if(arg1.equals(this.methodName)){
			this.methodAnalyser = new MethodAnalyzer();
			return this.methodAnalyser;
		}

        return null;
	}
	
	public Node getGraph(){
		return this.methodAnalyser.getGraph();
	}
	
	/*
	 * 	 Following methods are not used
	 */
	public void visit(int arg0, int arg1, String arg2, String arg3,
			String arg4, String[] arg5) {}

	public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) { return null; }

	public void visitAttribute(Attribute arg0) {}

	public void visitEnd() {}

	public FieldVisitor visitField(int arg0, String arg1, String arg2,
			String arg3, Object arg4) { return null; }

	public void visitInnerClass(String arg0, String arg1, String arg2, int arg3) {}

	public void visitOuterClass(String arg0, String arg1, String arg2) {}

	public void visitSource(String arg0, String arg1) {}
}
