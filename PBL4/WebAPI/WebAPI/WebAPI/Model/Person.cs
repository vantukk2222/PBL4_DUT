using Microsoft.OData.Edm;
using Microsoft.VisualBasic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WebAPI.Model
{
    [Table("Person")]
    public class Person
    {
        [Key]
        [Required]
        public int ID_Account { get; set; }

        [MaxLength(100)]
        public string? Name { get; set; }
        public bool? Gender { get; set; }
        public DateTime? DateOfBirth { get; set; }

        [MaxLength(100)]
        public string? Email { get; set; }

        [MaxLength(100)]
        public string? PhoneNumber { get; set; }

        [ForeignKey("ID_Account")]
        public virtual Account? Account { get; set; }
    }
}
