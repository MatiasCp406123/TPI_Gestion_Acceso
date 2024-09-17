@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movements_exit")
public class Movemennts_ExitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "movement_datetime")

    private Date movementDatetime;

    private String observations;

    @Column(name = "auth_ranges_id")
    private Integer authRangesId;

    @Column(name = "vehicles_id")
    private Integer vehiclesId;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_user")
    private String createdUser;

    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;

    @Column(name = "last_updated_user")
    private String lastUpdatedUser;
}
