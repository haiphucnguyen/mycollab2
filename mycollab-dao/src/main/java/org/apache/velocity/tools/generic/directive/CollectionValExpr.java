package org.apache.velocity.tools.generic.directive;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

import com.esofthead.mycollab.core.arguments.CollectionValueSearchField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class CollectionValExpr extends Directive {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#getName()
	 */
	public String getName() {
		return "collectionvalexpr";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#getType()
	 */
	public int getType() {
		return BLOCK;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.velocity.runtime.directive.Directive#render(org.apache.velocity
	 * .context.InternalContextAdapter, java.io.Writer,
	 * org.apache.velocity.runtime.parser.node.Node)
	 */
	public boolean render(InternalContextAdapter context, Writer writer,
			Node node) throws IOException, ResourceNotFoundException,
			ParseErrorException, MethodInvocationException {
		Object value = node.jjtGetChild(0).value(context);
		if (value instanceof CollectionValueSearchField) {
			Node content = node.jjtGetChild(1);
			content.render(context, writer);
		}
		return true;
	}
}
