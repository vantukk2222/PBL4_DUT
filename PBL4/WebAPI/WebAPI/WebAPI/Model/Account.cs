using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WebAPI.Model
{
    [Table("Account")]
    public class Account
    {
        public Account()
        {
            this.HoaDons = new HashSet<HoaDon>();
        }

        [Key]
        [Required]
        public int? ID_Account { get; set; }
        public string? UserName { get; set; }
        public string? Password { get; set; }
        public int? Position { get; set; }
        public virtual ICollection<HoaDon>? HoaDons { get; set; }
        public virtual Person? Person { get; set; }
    }
}
