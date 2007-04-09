package org.apache.maven.archiva.database.constraints;

import org.apache.maven.archiva.database.Constraint;

/**
 * ArchivaRepositoryByUrlConstraint 
 *
 * @author <a href="mailto:joakim@erdfelt.com">Joakim Erdfelt</a>
 * @version $Id$
 */
public class ArchivaRepositoryByUrlConstraint
    extends AbstractConstraint
    implements Constraint
{
    private String whereCondition;

    public ArchivaRepositoryByUrlConstraint( String url )
    {
        whereCondition = "this.url == '" + url + "'";
    }

    public String getWhereCondition()
    {
        return whereCondition;
    }

    public String getSortColumn()
    {
        return "url";
    }
}
