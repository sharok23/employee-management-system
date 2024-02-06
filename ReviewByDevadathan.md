# Service Class

#### Used an exception method "existsByName". I think there is no need of this exception. As every user have a unique id.

             if (employeeRepository.existsByName(request.getName())) {
                         throw new EntityAlreadyExistsException("Employee");
                     }