import { User } from "./user";
import { NaturalPerson } from "./naturalPerson";
import { Medicine } from "./medicine";
import { Institute } from "./instituteUser";

const naturalPerson = new NaturalPerson(1, 'Gabriel', '18-04-2006', 'São Leopoldo', '51982357261', 'gabriel@gmail.com','Alérgico a Seda', '190.290.390-49', 1)

const institute = new Institute(1, 'Lucas', '18-04-2006', 'São Leopoldo', '51912357481', 'luc@gmail.com','Nada a declarar', 1, '192.690.397-49')

const medication = new Medicine(1, 'Dipirona', 'Remédio para Dor', '2ml', '8/8 horas', 'Gotas')