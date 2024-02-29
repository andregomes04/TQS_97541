a) Identify a couple of examples that use AssertJ expressive methods chaining:

        -> in A_EmployeeRepositoryTest:

            Employee fromDb = employeeRepository.findById(emp.getId()).orElse(null);
            assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
            
        -> in D_EmployeeRestControllerIT: 

            assertThat(found).extracting(Employee::getName).containsOnly("bob");

b) Identify an example in which you mock the behavior of the repository (and avoid involving a 
database).

        -> in B_EmployeeService_UnitTest:

            @Mock( lenient = true)
            private EmployeeRepository employeeRepository;

            @InjectMocks
            private EmployeeServiceImpl employeeService;

            @BeforeEach
            public void setUp() {

                Employee john = new Employee("john", "john@deti.com");
                john.setId(111L);

                Employee bob = new Employee("bob", "bob@deti.com");
                Employee alex = new Employee("alex", "alex@deti.com");

                List<Employee> allEmployees = Arrays.asList(john, bob, alex);

                Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
                Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
                Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
                Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
                Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
                Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
            }

c) What is the difference between standard @Mock and @MockBean?

        Standard @Mock comes from Mockito, while @MockBean comes from SpringBoot Test; @Mock is used to mock objects in unit tests, while @MockBean is used to mock Spring Beans in integration tests; 

d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

        This file provides configuration settings for the Spring Boot application's data source, allowing it to and interact with a MySQL database. It is used during tests.

e) The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences? 

        -> C:
            Here we test the controllers using mocks of the required services, using MockMvc;
        -> D:
            Here we conduct an integration test involving all components, needing a real database while still using MockMvc;
        -> E:
            Similar to scenario D, this test utilizes a genuine HTTP Client, specifically TestRestTemplate;