package demo.jpa.entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Enumerated(EnumType.ORDINAL)
  private ProjectType type;
  
  private String name;
  private String description;
  private BigDecimal budget;
  
  @ManyToOne
  @JoinColumn(name = "leader_id")
  private Employee leader;
  
  @ManyToMany
  // JoinTable定义中间表； 
  // joinColumns定义中间表中"我方"的字段名称；
  // inverseJoinColumns定义中间表中"另一方"的字段名称
  @JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "project_id"), 
      inverseJoinColumns = @JoinColumn(name = "employee_id")) 
  private Collection<Employee> employees;

  public Long getId() {
    return id;
  }

  public Project setId(Long id) {
    this.id = id;
    return this;
  }

  public ProjectType getType() {
    return type;
  }

  public Project setType(ProjectType type) {
    this.type = type;
    return this;
  }

  public String getName() {
    return name;
  }

  public Project setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Project setDescription(String description) {
    this.description = description;
    return this;
  }

  public BigDecimal getBudget() {
    return budget;
  }

  public Project setBudget(BigDecimal budget) {
    this.budget = budget;
    return this;
  }

  public Employee getLeader() {
    return leader;
  }

  public Project setLeader(Employee leader) {
    this.leader = leader;
    return this;
  }

  public Collection<Employee> getEmployees() {
    return Collections.unmodifiableCollection(employees);
  }
  
  public Project addEmployee(Employee employee) {
    employees.add(employee);
    return this;
  }

  public Project addAllEmployees(Collection<? extends Employee> employees) {
    this.employees.addAll(employees);
    return this;
  }
}