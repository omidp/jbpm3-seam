/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jbpm.ejb.impl;

import org.jbpm.JbpmContext;
import org.jbpm.command.Command;
import org.jbpm.job.Job;

/**
 * Batch job processing command.
 * 
 * @author Alejandro Guizar
 */
public class ExecuteJobsCommand implements Command {

  private long[] jobIds;

  private static final long serialVersionUID = 1L;

  public ExecuteJobsCommand(long[] jobIds) {
    if (jobIds == null) throw new IllegalArgumentException("jobIds is null");
    this.jobIds = jobIds;
  }

  public Object execute(JbpmContext jbpmContext) throws Exception {
    for (int i = 0; i < jobIds.length; i++) {
      Job job = ExecuteJobCommand.acquireJob(jobIds[i], jbpmContext);
      if (job != null) ExecuteJobCommand.executeJob(job, jbpmContext);
    }
    return null;
  }
}
