/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.xml.ws;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Action} annotation allows explicit association of a
 * WS-Addressing {@code Action} message addressing property with
 * {@code input}, {@code output}, and
 * {@code fault} messages of the mapped WSDL operation.
 * <p>
 * This annotation can be specified on each method of a service endpoint interface.
 * For such a method, the mapped operation in the generated WSDL's
 * {@code wsam:Action} attribute on the WSDL {@code input},
 * {@code output} and {@code fault} messages of the WSDL {@code operation}
 * is based upon which attributes of the {@code Action} annotation have been specified.
 * For the exact computation of {@code wsam:Action} values for the messages, refer
 * to the algorithm in the JAX-WS specification.
 * <p>
 * <b>Example 1</b>: Specify explicit values for {@code Action} message addressing property
 * for {@code input} and {@code output} messages.
 *
 * <pre>
 * {@literal @}WebService(targetNamespace="http://example.com/numbers")
 *  public class AddNumbersImpl {
 *     <b>{@literal @}Action(
 *          input="http://example.com/inputAction",
 *          output="http://example.com/outputAction")</b>
 *      public int addNumbers(int number1, int number2) {
 *          return number1 + number2;
 *      }
 *  }
 * </pre>
 *
 * The generated WSDL looks like:
 * <pre> {@code
 *   <definitions targetNamespace="http://example.com/numbers" ...>
 *     ...
 *     <portType name="AddNumbersPortType">
 *       <operation name="AddNumbers">
 *         <input message="tns:AddNumbersInput" name="foo"
 *           <b>wsam:Action="http://example.com/inputAction"</b>/>
 *         <output message="tns:AddNumbersOutput" name="bar"
 *           <b>wsam:Action="http://example.com/outputAction"</b>/>
 *       </operation>
 *     </portType>
 *     ...
 *   </definitions>
 * }
 * </pre>
 *
 * <p>
 * <b>Example 2</b>: Specify explicit value for {@code Action} message addressing property
 * for only the {@code input} message. The {@code wsam:Action} values for the
 * WSDL {@code output} message are computed using the algorithm in the JAX-WS specification.
 *
 * <pre>
 * {@literal @}WebService(targetNamespace="http://example.com/numbers")
 *  public class AddNumbersImpl {
 *     <b>{@literal @}Action(input="http://example.com/inputAction")</b>
 *      public int addNumbers(int number1, int number2) {
 *          return number1 + number2;
 *      }
 *  }
 * </pre>
 *
 * The generated WSDL looks like:
 * <pre> {@code
 *   <definitions targetNamespace="http://example.com/numbers" ...>
 *     ...
 *     <portType name="AddNumbersPortType">
 *       <operation name="AddNumbers">
 *         <input message="tns:AddNumbersInput" name="foo"
 *           <b>wsam:Action="http://example.com/inputAction"</b>/>
 *         <output message="tns:AddNumbersOutput" name="bar"
 *           <b>wsam:Action="http://example.com/numbers/AddNumbersPortType/AddNumbersResponse"</b>/>
 *       </operation>
 *     </portType>
 *     ...
 *   </definitions>
 * }</pre>
 *
 * It is legitimate to specify an explicit value for {@code Action} message addressing property for
 * {@code output} message only. In this case, {@code wsam:Action} value for the
 * WSDL {@code input} message is computed using the algorithm in the JAX-WS specification.
 *
 * <p>
 * <b>Example 3</b>: See {@link FaultAction} annotation for an example of
 * how to specify an explicit value for {@code Action} message addressing property for the
 * {@code fault} message.
 *
 * @see FaultAction
 *
 * @since 1.6, JAX-WS 2.1
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Action {
    /**
     * Explicit value of the WS-Addressing {@code Action} message addressing property for the {@code input}
     * message of the operation.
     *
     * @return {@code Action} message addressing property for the {@code input} message
     */
    String input() default "";

    /**
     * Explicit value of the WS-Addressing {@code Action} message addressing property for the {@code output}
     * message of the operation.
     *
     * @return {@code Action} message addressing property for the {@code output} message
     */
    String output() default "";

    /**
     * Explicit value of the WS-Addressing {@code Action} message addressing property for the {@code fault}
     * message(s) of the operation. Each exception that is mapped to a fault and requires an explicit WS-Addressing
     * {@code Action} message addressing property, needs to be specified as a value in this property
     * using {@link FaultAction} annotation.
     *
     * @return {@code Action} message addressing property for the {@code fault} message(s)
     */
    FaultAction[] fault() default { };
}
