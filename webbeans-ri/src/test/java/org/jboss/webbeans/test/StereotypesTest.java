package org.jboss.webbeans.test;

import static org.jboss.webbeans.test.util.Util.createSimpleModel;
import static org.jboss.webbeans.test.util.Util.getEmptyAnnotatedType;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.webbeans.DefinitionException;
import javax.webbeans.RequestScoped;

import org.jboss.webbeans.introspector.AnnotatedClass;
import org.jboss.webbeans.introspector.SimpleAnnotatedClass;
import org.jboss.webbeans.model.StereotypeModel;
import org.jboss.webbeans.model.bean.SimpleBeanModel;
import org.jboss.webbeans.test.annotations.AnimalOrderStereotype;
import org.jboss.webbeans.test.annotations.AnimalStereotype;
import org.jboss.webbeans.test.annotations.HornedAnimalDeploymentType;
import org.jboss.webbeans.test.annotations.HornedMammalStereotype;
import org.jboss.webbeans.test.annotations.RequestScopedAnimalStereotype;
import org.jboss.webbeans.test.annotations.Synchronous;
import org.jboss.webbeans.test.annotations.Tame;
import org.jboss.webbeans.test.annotations.broken.StereotypeWithBindingTypes;
import org.jboss.webbeans.test.annotations.broken.StereotypeWithNonEmptyNamed;
import org.jboss.webbeans.test.annotations.broken.StereotypeWithTooManyDeploymentTypes;
import org.jboss.webbeans.test.annotations.broken.StereotypeWithTooManyScopeTypes;
import org.jboss.webbeans.test.beans.Animal;
import org.jboss.webbeans.test.beans.Chair;
import org.jboss.webbeans.test.beans.Goldfish;
import org.jboss.webbeans.test.beans.HighlandCow;
import org.jboss.webbeans.test.beans.Order;
import org.jboss.webbeans.test.beans.broken.Carp;
import org.jboss.webbeans.test.bindings.HornedMamalStereotypeAnnotationLiteral;
import org.jboss.webbeans.test.bindings.SynchronousAnnotationLiteral;
import org.testng.annotations.Test;

@SpecVersion("PDR")
public class StereotypesTest extends AbstractTest
{
	
   @Test(groups="annotationDefinition", expectedExceptions=DefinitionException.class) @SpecAssertion(section="2.7.1")
   public void testHasCorrectTarget()
   {
	   assert false;
   }
   
   @Test(groups="annotationDefinition", expectedExceptions=DefinitionException.class) @SpecAssertion(section="2.7.1")
   public void testHasCorrectRetention()
   {
	   assert false;
   }
   
   @Test(groups="annotationDefinition", expectedExceptions=DefinitionException.class) @SpecAssertion(section="2.7.1")
   public void testHasStereotypeAnnotation()
   {
	   assert false;
   }
   
   @Test
   public void testAnimalStereotype()
   {
      StereotypeModel<AnimalStereotype> animalStereotype = new StereotypeModel<AnimalStereotype>(AnimalStereotype.class);
      assert animalStereotype.getDefaultScopeType().annotationType().equals(RequestScoped.class);
      assert animalStereotype.getInterceptorBindings().size() == 0;
      assert animalStereotype.getRequiredTypes().size() == 1;
      assert animalStereotype.getRequiredTypes().contains(Animal.class);
      assert animalStereotype.getSupportedScopes().size() == 0;
      assert !animalStereotype.isBeanNameDefaulted();
      assert animalStereotype.getDefaultDeploymentType() == null;
   }
   
   @Test
   public void testAnimalOrderStereotype()
   {
      StereotypeModel<AnimalOrderStereotype> animalStereotype = new StereotypeModel<AnimalOrderStereotype>(AnimalOrderStereotype.class);
      assert animalStereotype.getDefaultScopeType() == null;
      assert animalStereotype.getInterceptorBindings().size() == 0;
      assert animalStereotype.getRequiredTypes().size() == 2;
      Class<?> [] requiredTypes = {Animal.class, Order.class};
      assert animalStereotype.getRequiredTypes().containsAll(Arrays.asList(requiredTypes));
      assert animalStereotype.getSupportedScopes().size() == 0;
      assert !animalStereotype.isBeanNameDefaulted();
      assert animalStereotype.getDefaultDeploymentType() == null;
   }
   
   @Test
   public void testRequestScopedAnimalStereotype()
   {
      StereotypeModel<RequestScopedAnimalStereotype> animalStereotype = new StereotypeModel<RequestScopedAnimalStereotype>(RequestScopedAnimalStereotype.class);
      assert animalStereotype.getDefaultScopeType() == null;
      assert animalStereotype.getInterceptorBindings().size() == 0;
      assert animalStereotype.getRequiredTypes().size() == 1;
      assert Animal.class.equals(animalStereotype.getRequiredTypes().iterator().next());
      assert animalStereotype.getSupportedScopes().size() == 1;
      assert animalStereotype.getSupportedScopes().contains(RequestScoped.class);
      assert !animalStereotype.isBeanNameDefaulted();
      assert animalStereotype.getDefaultDeploymentType() == null;
   }
   
   @Test @SpecAssertion(section="2.7.1")
   public void testStereotypeWithScopeType()
   {
	   StereotypeModel<AnimalStereotype> animalStereotype = new StereotypeModel<AnimalStereotype>(AnimalStereotype.class);
	   assert animalStereotype.getDefaultScopeType().annotationType().equals(RequestScoped.class);
   }
   
   @Test @SpecAssertion(section="2.7.1")
   public void testStereotypeWithoutScopeType()
   {
	   StereotypeModel<HornedMammalStereotype> animalStereotype = new StereotypeModel<HornedMammalStereotype>(HornedMammalStereotype.class);
	   assert animalStereotype.getDefaultScopeType() == null;
   }
   
   @Test @SpecAssertion(section="2.7.1")
   public void testStereotypeWithoutInterceptors()
   {
      StereotypeModel<AnimalStereotype> animalStereotype = new StereotypeModel<AnimalStereotype>(AnimalStereotype.class);
      assert animalStereotype.getInterceptorBindings().size() == 0;
   }
   
   @Test(groups="interceptors") @SpecAssertion(section="2.7.1")
   public void testStereotypeWithInterceptors()
   {
      assert false;
   }
   
   @Test(expectedExceptions=DefinitionException.class) @SpecAssertion(section="2.7.1")
   public void testStereotypeWithTooManyScopeTypes()
   {
      new StereotypeModel<StereotypeWithTooManyScopeTypes>(StereotypeWithTooManyScopeTypes.class);
   }
   
   @Test(expectedExceptions=DefinitionException.class) @SpecAssertion(section="2.7.1")
   public void testStereotypeWithTooManyDeploymentTypes()
   {
      new StereotypeModel<StereotypeWithTooManyDeploymentTypes>(StereotypeWithTooManyDeploymentTypes.class);
   }
   
   @Test(expectedExceptions=DefinitionException.class) @SpecAssertion(section="2.7.1")
   public void testStereotypeWithNonEmptyNamed()
   {
      new StereotypeModel<StereotypeWithNonEmptyNamed>(StereotypeWithNonEmptyNamed.class);
   }
   
   @Test(expectedExceptions=DefinitionException.class) @SpecAssertion(section="2.7.1")
   public void testStereotypeWithBindingTypes()
   {
      new StereotypeModel<StereotypeWithBindingTypes>(StereotypeWithBindingTypes.class);
   }
   
   @Test @SpecAssertion(section={"2.7.2", "2.7.4"})
   public void testMultipleStereotypes()
   {
      SimpleBeanModel<HighlandCow> highlandCow = new SimpleBeanModel<HighlandCow>(new SimpleAnnotatedClass<HighlandCow>(HighlandCow.class), getEmptyAnnotatedType(HighlandCow.class), manager);
      assert highlandCow.getName() == null;
      assert highlandCow.getBindingTypes().iterator().next().annotationType().equals(Tame.class);
      assert highlandCow.getScopeType().equals(RequestScoped.class);
      assert highlandCow.getMergedStereotypes().getRequiredTypes().size() == 1;
      assert highlandCow.getMergedStereotypes().getRequiredTypes().contains(Animal.class);
      assert highlandCow.getMergedStereotypes().getSupportedScopes().size() == 1;
      assert highlandCow.getMergedStereotypes().getSupportedScopes().contains(RequestScoped.class);
      assert highlandCow.getDeploymentType().equals(HornedAnimalDeploymentType.class);
   }
   
   @Test @SpecAssertion(section="2.7.3")
   public void testStereotypeDeclaredInXmlIgnoresJavaStereotypes()
   {
      Map<Class<? extends Annotation>, Annotation> cooXmlAnnotations = new HashMap<Class<? extends Annotation>, Annotation>();
      cooXmlAnnotations.put(HornedMammalStereotype.class, new HornedMamalStereotypeAnnotationLiteral());
      AnnotatedClass<HighlandCow> cooXmlAnnotatedItem = new SimpleAnnotatedClass<HighlandCow>(HighlandCow.class, cooXmlAnnotations);
      
      SimpleBeanModel<HighlandCow> coo = createSimpleModel(HighlandCow.class, cooXmlAnnotatedItem, manager);
      assert coo.getDeploymentType().equals(HornedAnimalDeploymentType.class);
      assert coo.getScopeType().equals(RequestScoped.class);
      assert coo.getMergedStereotypes().getRequiredTypes().size() == 1;
      assert coo.getMergedStereotypes().getRequiredTypes().contains(Animal.class);
      assert coo.getMergedStereotypes().getSupportedScopes().size() == 0;
   }
   
   @Test @SpecAssertion(section="2.7.3")
   public void testStereotypeDeclaredInXmlIgnoresJavaBindingTypes()
   {
      Map<Class<? extends Annotation>, Annotation> cooXmlAnnotations = new HashMap<Class<? extends Annotation>, Annotation>();
      cooXmlAnnotations.put(HornedMammalStereotype.class, new HornedMamalStereotypeAnnotationLiteral());
      cooXmlAnnotations.put(Synchronous.class, new SynchronousAnnotationLiteral());
      AnnotatedClass<HighlandCow> cooXmlAnnotatedItem = new SimpleAnnotatedClass<HighlandCow>(HighlandCow.class, cooXmlAnnotations);
      
      SimpleBeanModel<HighlandCow> coo = createSimpleModel(HighlandCow.class, cooXmlAnnotatedItem, manager);
      assert coo.getBindingTypes().size() == 1;
      assert coo.getBindingTypes().contains(new SynchronousAnnotationLiteral());
      
   }
   
   @Test@SpecAssertion(section="2.7.4")
   public void testRequiredTypeIsImplemented()
   {
         new SimpleBeanModel<HighlandCow>(new SimpleAnnotatedClass<HighlandCow>(HighlandCow.class), getEmptyAnnotatedType(HighlandCow.class), manager);
   }
   
   @Test(expectedExceptions=DefinitionException.class) @SpecAssertion(section="2.7.4")
   public void testRequiredTypeIsNotImplemented()
   {
      new SimpleBeanModel<Chair>(new SimpleAnnotatedClass<Chair>(Chair.class), getEmptyAnnotatedType(Chair.class), manager);      
   }
   
   @Test @SpecAssertion(section="2.7.4")
   public void testScopeIsSupported()
   {
      new SimpleBeanModel<Goldfish>(new SimpleAnnotatedClass<Goldfish>(Goldfish.class), getEmptyAnnotatedType(Goldfish.class), manager);
   }
   
   @Test(expectedExceptions=DefinitionException.class) @SpecAssertion(section="2.7.4")
   public void testScopeIsNotSupported()
   {
      new SimpleBeanModel<Carp>(new SimpleAnnotatedClass<Carp>(Carp.class), getEmptyAnnotatedType(Carp.class), manager);    
   }
   
}
