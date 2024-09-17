@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movements_entry")
public class Movements_EntryEntity {
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

    @Column(name = "created_user")
    private Integer createdUser;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "last_updated_user")
    private Integer lastUpdatedUser;

    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;
}
