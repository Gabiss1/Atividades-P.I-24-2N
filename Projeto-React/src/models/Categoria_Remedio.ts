import { Entity, PrimaryGeneratedColumn, Column, OneToOne } from "typeorm";
import { Remedio } from "./Remedio";

@Entity()
export class Categoria_Remedio {
  @PrimaryGeneratedColumn()
  categoria_Id!: number;

  @Column()
  categoria_Nome!: string;

  @Column({ unique: true })
  categoria_Descricao!: string;

  @Column({ unique: true })
  categoria_Posologia!: string;

  @OneToOne(() => Remedio, (remedio) => remedio.Fk_Categoria_Remedio, {
    nullable: false,
    onDelete: "CASCADE",
  })
  remedio!: Remedio;
}